package com.phenix.repository;

import com.phenix.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    /**
     * 注意：添加@Transactional注解，测试完毕后会自动回滚
     */
    @Test
    @Transactional
    public void saveOne() {
        ProductCategory productCategory = new ProductCategory("favorite", 2);
        ProductCategory result = repository.save(productCategory);
        assertNotNull(result);
    }

    @Test
    public void findOneTest() {
        Optional<ProductCategory> productCategory = repository.findById(1);
        productCategory.ifPresent(System.out::println);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1, 2);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        assertNotEquals(0, result.size());
    }
}