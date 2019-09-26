package com.phenix.controller;

import com.phenix.converter.OrderFormToOrderDTOConverter;
import com.phenix.dto.OrderDTO;
import com.phenix.enums.Result;
import com.phenix.exception.SellException;
import com.phenix.form.OrderForm;
import com.phenix.service.BuyerService;
import com.phenix.service.OrderService;
import com.phenix.util.ResultUtils;
import com.phenix.vo.ResultVO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * BuyerOrderController
 *
 * @author john
 * @since 2018-12-14
 */
@Api(value = "/buyer/order", tags = "订单")
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    private final OrderService orderService;
    private final BuyerService buyerService;

    @Autowired
    public BuyerOrderController(OrderService orderService,
                                BuyerService buyerService) {
        this.orderService = orderService;
        this.buyerService = buyerService;
    }


    /**
     * 创建订单
     *
     * @param orderForm     订单表单
     * @param bindingResult 绑定结果
     * @return 创建结果
     */
    @ApiOperation(value = "创建订单", notes = "必须参数:用户名,地址,电话,openid,购物车不能为空")
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确, orderForm={}", orderForm);
            throw new SellException(Result.PARAM_ERROR.getCode(), Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车为空");
            throw new SellException(Result.ORDER_CART_EMPTY);
        }
        OrderDTO createResult = orderService.createOrder(orderDTO);
        Map<String, String> mapData = new HashMap<>();
        mapData.put("orderId", createResult.getOrderId());
        return ResultUtils.success(mapData);
    }

    /**
     * 订单列表
     *
     * @param openid 微信openid
     * @param page   分页
     * @param size   分页大小
     * @return 订单列表数据
     */
    @ApiOperation(value = "查询订单", notes = "必须参数:openid, page, size")
    @GetMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("[订单列表]参数openid不能为空");
            throw new SellException(Result.PARAM_ERROR);
        }

        List<OrderDTO> orderDTOList = orderService.findList(openid, PageRequest.of(page, size)).getContent();
        return ResultUtils.success(orderDTOList);
    }

    /**
     * 订单详情
     *
     * @param openid  微信openid
     * @param orderId 订单编号
     * @return 订单详情数据
     */
    @ApiOperation(value = "查询订单详情", notes = "必须参数:openid, orderId")
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") @ApiParam(value = "微信openid", defaultValue = "123123123", required = true) String openid,
                           @RequestParam("orderId") @ApiParam(value = "订单编号", defaultValue = "cd9f8b734c8d4fa38159fe5e452d7e29", required = true) String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultUtils.success(orderDTO);
    }

    /**
     * 取消订单
     *
     * @param openid  微信openid
     * @param orderId 订单编号
     * @return 订单详情数据
     */
    @ApiOperation(value = "取消订单", notes = "必须参数:openid, orderId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "微信openid", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "orderId", value = "订单编号", paramType = "query", dataType = "String", required = true)
    })
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        if (orderDTO == null) {
            return ResultUtils.success(Result.ORDER_NOT_EXIST);
        }
        return ResultUtils.success();
    }
}
