package com.phenix.service;

import com.phenix.dto.OrderDTO;
import com.phenix.entity.OrderDetail;
import com.phenix.enums.OrderStatus;
import com.phenix.enums.PayStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceImplTest {

    private static final String BUYER_OPENID = "12341234123";
    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("John");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("12341234123");
        orderDTO.setBuyerAddress("Chengdu");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetailOne = new OrderDetail();
        orderDetailOne.setProductId("123456");
        orderDetailOne.setProductQuantity(1);

        OrderDetail orderDetailTwo = new OrderDetail();
        orderDetailTwo.setProductId("123457");
        orderDetailTwo.setProductQuantity(1);

        orderDetailList.add(orderDetailOne);
        orderDetailList.add(orderDetailTwo);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.createOrder(orderDTO);

        System.out.println("[创建订单] result={}" + result.toString());

    }

    @Test
    public void findOne() {
        String orderId = "098a6031a71a4d3886e9d51394bb9a54";
        OrderDTO result = orderService.findOne(orderId);
        System.out.println("[查找一个订单]" + result.toString());
    }

    @Test
    public void findList() {
        Page<OrderDTO> result = orderService.findList(BUYER_OPENID, PageRequest.of(0, 10));
        System.out.println("[查找多个订单]" + result.getContent());
    }

    @Test
    public void cancel() {
        String orderId = "098a6031a71a4d3886e9d51394bb9a54";
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        assertEquals(OrderStatus.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        String orderId = "2ba4ad25351549d2abf07455527c3e9f";
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.finish(orderDTO);
        assertEquals(OrderStatus.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        String orderId = "5f49cf6745d34b2285345a9bf4af39d2";
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.paid(orderDTO);
        assertEquals(PayStatus.SUCCESS.getCode(), result.getPayStatus());
    }
}