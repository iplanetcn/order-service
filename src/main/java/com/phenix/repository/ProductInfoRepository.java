package com.phenix.repository;

import com.phenix.entity.ProductInfo;
import com.phenix.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductInfoRepository
 *
 * @author john
 * @since 2018-12-08
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 根据状态查找商品信息列表
     *
     * @param status 商品状态 {@link ProductStatus#getCode()}
     * @return 商品信息列表
     */
    List<ProductInfo> findByProductStatus(Integer status);
}
