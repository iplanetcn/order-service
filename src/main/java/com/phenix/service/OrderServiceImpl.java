package com.phenix.service;

import com.phenix.dto.OrderDTO;
import com.phenix.entity.OrderDetail;
import com.phenix.entity.ProductInfo;
import com.phenix.repository.OrderDetailRepository;
import com.phenix.repository.OrderMasterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderMasterRepository orderMasterRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductService productService;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        // 1.查询商品(数量,价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
        }
        // 2.计算总价
        // 3.写入订单数据库(order_master, order_detail)
        // 4.减库存
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
