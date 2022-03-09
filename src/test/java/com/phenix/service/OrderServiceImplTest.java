package com.phenix.service;

import com.phenix.dto.OrderDTO;
import com.phenix.entity.OrderDetail;
import com.phenix.entity.ProductInfo;
import com.phenix.enums.OrderStatus;
import com.phenix.enums.PayStatus;
import com.phenix.repository.ProductInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceImplTest {

    private static final String BUYER_OPENID = "12341234123";

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductInfoRepository repository;

    @BeforeEach
    void setUp() {
        ProductInfo productInfo1 = new ProductInfo();
        productInfo1.setProductId("123456");
        productInfo1.setProductName("皮蛋瘦肉粥");
        productInfo1.setProductDescription("皮蛋瘦肉粥,营养美味，居家必备。");
        productInfo1.setProductPrice(new BigDecimal("3.50"));
        productInfo1.setProductStack(50);
        productInfo1.setProductIcon("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3478414879,3360474584&fm=85&s=D828AA551713666B107154640300407B");
        productInfo1.setCategoryType(1);
        productInfo1.setProductStatus(0);
        ProductInfo result1 = repository.save(productInfo1);
        assertNotNull(result1);

        ProductInfo productInfo2 = new ProductInfo();
        productInfo2.setProductId("123457");
        productInfo2.setProductName("fry egg");
        productInfo2.setProductDescription("营养美味，居家必备。");
        productInfo2.setProductPrice(new BigDecimal("3.50"));
        productInfo2.setProductStack(50);
        productInfo2.setProductIcon("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3478414879,3360474584&fm=85&s=D828AA551713666B107154640300407B");
        productInfo2.setCategoryType(1);
        productInfo2.setProductStatus(0);
        ProductInfo result2 = repository.save(productInfo2);
        assertNotNull(result2);
    }

    @AfterEach
    void tearDown() {
        repository.deleteById("123456");
        repository.deleteById("123457");
    }

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
        OrderDTO order = orderService.createOrder(orderDTO);
        assertNotNull(order);
    }

    private String createTestOrder() {
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
        OrderDTO order = orderService.createOrder(orderDTO);
        return order.getOrderId();
    }

    @Test
    public void findOne() {
        String orderId = createTestOrder();
        OrderDTO result = orderService.findOne(orderId);
        System.out.println("[查找一个订单]" + result.toString());
    }

    @Test
    public void findList() {
        createTestOrder();
        Page<OrderDTO> result = orderService.findList(BUYER_OPENID, PageRequest.of(0, 10));
        System.out.println("[查找多个订单]" + result.getContent());
    }

    @Test
    public void cancel() {
        String orderId = createTestOrder();
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        assertEquals(OrderStatus.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        String orderId = createTestOrder();
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.finish(orderDTO);
        assertEquals(OrderStatus.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        String orderId = createTestOrder();
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.paid(orderDTO);
        assertEquals(PayStatus.SUCCESS.getCode(), result.getPayStatus());
    }
}