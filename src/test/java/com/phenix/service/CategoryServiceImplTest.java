package com.phenix.service;

import com.phenix.entity.ProductCategory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    private void saveOne() {
        ProductCategory pd = new ProductCategory("test", 2);
        ProductCategory result = categoryService.save(pd);
        assertNotNull(result);
    }

    @Test
    public void findOne() {
        saveOne();
        ProductCategory productCategory = categoryService.findOne(1);
        assertEquals(Integer.valueOf(1), Objects.requireNonNull(productCategory).getCategoryId());
    }

    @Test
    public void findAll() {
        saveOne();
        List<ProductCategory> all = categoryService.findAll();
        assertNotEquals(0, all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        saveOne();
        List<Integer> list = Lists.newArrayList(1, 2);
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(list);
        assertNotEquals(0, result.size());
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory pd = new ProductCategory("test", 3);
        ProductCategory result = categoryService.save(pd);
        assertNotNull(result);
    }
}