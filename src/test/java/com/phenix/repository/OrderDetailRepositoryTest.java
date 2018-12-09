package com.phenix.repository;

import com.phenix.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;
    private final String ORDER_ID = "123456";

    @Test
    @Transactional
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(ORDER_ID);
        orderDetail.setDetailId("12345678");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(3.50));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3478414879,3360474584&fm=85&s=D828AA551713666B107154640300407B");

        OrderDetail result = repository.save(orderDetail);

        assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = repository.findByOrderId(ORDER_ID);

        assertNotEquals(0, result.size());
    }
}