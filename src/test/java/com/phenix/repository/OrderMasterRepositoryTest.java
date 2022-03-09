package com.phenix.repository;

import com.phenix.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;
    private static final String OPENID = "10000";

    @Test
    @Transactional
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("John");
        orderMaster.setBuyerPhone("12341234123");
        orderMaster.setBuyerAddress("test address");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(BigDecimal.valueOf(2.3));

        OrderMaster result = repository.save(orderMaster);
        assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        saveTest();
        Page<OrderMaster> result = repository.findAllByBuyerOpenid(OPENID, PageRequest.of(0, 8));
        assertNotEquals(0, result.getTotalElements());
    }
}