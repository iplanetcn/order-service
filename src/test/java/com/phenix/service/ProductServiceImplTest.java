package com.phenix.service;

import com.phenix.entity.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo one = productService.findProductId("123456");
        assertNotNull(one);
    }

    @Test
    public void findAvailableProduct() {
        List<ProductInfo> availableProduct = productService.findUpAll();
        assertNotEquals(0, availableProduct.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<ProductInfo> result = productService.findAll(pageRequest);
        assertNotEquals(0, result.getTotalElements());
    }

    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("江油肥肠");
        productInfo.setProductDescription("江油肥肠,肥而不腻，美味可口。");
        productInfo.setProductPrice(new BigDecimal("10.00"));
        productInfo.setProductStack(50);
        productInfo.setProductIcon("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3478414879,3360474584&fm=85&s=D828AA551713666B107154640300407B");
        productInfo.setCategoryType(2);
        productInfo.setProductStatus(0);
        ProductInfo result = productService.save(productInfo);
        assertNotNull(result);
    }
}