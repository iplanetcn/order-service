package com.phenix.repository;

import com.phenix.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderMasterRepository
 *
 * @author john
 * @since 2018-12-08
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 根据买家微信Openid查询订单信息
     *
     * @param buyerOpenid 买家微信Openid
     * @param pageable    分页
     * @return 订单主表分页
     */
    Page<OrderMaster> findAllByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
