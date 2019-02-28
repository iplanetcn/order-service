package com.phenix.service;

import com.phenix.dto.CartDTO;
import com.phenix.dto.OrderDTO;
import com.phenix.entity.OrderDetail;
import com.phenix.entity.OrderMaster;
import com.phenix.entity.ProductInfo;
import com.phenix.enums.OrderStatus;
import com.phenix.enums.PayStatus;
import com.phenix.exception.SellException;
import com.phenix.repository.OrderDetailRepository;
import com.phenix.repository.OrderMasterRepository;
import com.phenix.util.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.phenix.enums.Result.*;

/**
 * OrderService实现类 添加@Transactional 如果抛异常,则回滚数据
 *
 * @author john
 * @since 2018-12-13
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderMasterRepository orderMasterRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderMasterRepository orderMasterRepository,
                            OrderDetailRepository orderDetailRepository,
                            ProductService productService) {
        this.orderMasterRepository = orderMasterRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        String orderId = KeyUtils.genUUID();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 查询商品(数量,价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findProductId(orderDetail.getProductId());
            if (productInfo == null) { throw new SellException(PRODUCT_NOT_EXIST); }

            // 计算订单总价
            orderAmount = productInfo.getProductPrice()
                                     .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                                     .add(orderAmount);
            // 订单详情入库
            BeanUtils.copyProperties(productInfo, orderDetail, "createTime", "updateTime");
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setCreateTime(null);
            orderDetail.setUpdateTime(null);
            orderDetailRepository.save(orderDetail);
        }

        // 写入订单数据库(order_master, order_detail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster, "orderStatus", "payStatus", "createTime", "updateTime");
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        // 减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                                            .stream()
                                            .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                                            .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        // 查询订单主表
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null) { throw new SellException(ORDER_NOT_EXIST); }

        // 查询订单详情表
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) { throw new SellException(ORDER_DETAIL_NOT_EXIST); }

        // 组合查询结果
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        // 查询openid对应所有的订单主表
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAllByBuyerOpenid(buyerOpenid, pageable);
        if (orderMasterPage.getTotalElements() == 0) {
            throw new SellException(ORDER_NOT_EXIST);
        }

        // 转换数据
        return orderMasterPage.map(orderMaster -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            return orderDTO;
        });
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            throw new SellException(ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatus.CANCEL.getCode());
        updateOrderMaster(orderDTO);

        // 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new SellException(ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                                            .stream()
                                            .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                                            .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        // 如果已支付,需要退款
        if (orderDTO.getPayStatus().equals(PayStatus.SUCCESS.getCode())) {
            //TODO implement payment
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            throw new SellException(ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatus.FINISHED.getCode());
        updateOrderMaster(orderDTO);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            throw new SellException(ORDER_STATUS_ERROR);
        }

        // 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatus.WAIT.getCode())) {
            throw new SellException(ORDER_PAY_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
        updateOrderMaster(orderDTO);
        return orderDTO;
    }


    /**
     * 更新订单主表
     *
     * @param orderDTO 订单数据
     */
    @Transactional
    public void updateOrderMaster(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            throw new SellException(ORDER_UPDATE_FAIL);
        }
    }
}
