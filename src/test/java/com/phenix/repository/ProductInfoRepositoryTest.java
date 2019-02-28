package com.phenix.repository;

import com.phenix.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;

    @Test
    @Transactional
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋瘦肉粥");
        productInfo.setProductDescription("皮蛋瘦肉粥,营养美味，居家必备。");
        productInfo.setProductPrice(new BigDecimal(3.50));
        productInfo.setProductStack(50);
        productInfo.setProductIcon("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3478414879,3360474584&fm=85&s=D828AA551713666B107154640300407B");
        productInfo.setCategoryType(1);
        productInfo.setProductStatus(0);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindByProductStatus() {
        List<ProductInfo> result = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void testFindByProductId() {
        ProductInfo result = repository.findById("123456").orElse(null);
        Assert.assertNotNull(result);
    }
}