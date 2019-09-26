package com.phenix.service;

import com.phenix.dto.OrderDTO;
import com.phenix.enums.Result;
import com.phenix.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    private final OrderService orderService;

    @Autowired
    public BuyerServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("[取消订单]查询订单为空");
            throw new SellException(Result.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }


    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);

        if (orderDTO == null) {
            return null;
        }

        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("[订单详情]订单openid不一致,openid={},orderDTO={}", openid, orderDTO);
            throw new SellException(Result.ORDER_OWNER_ERROR);
        }

        return orderDTO;
    }

}
