package com.phenix.repository;

import com.phenix.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductCategoryRepository
 *
 * @author john
 * @since 2018-12-08
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 输入编号查找商品类目
     *
     * @param list 输入商品类目编号
     * @return 商品类目列表
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
