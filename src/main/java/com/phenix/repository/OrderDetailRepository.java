package com.phenix.repository;

import com.phenix.entity.OrderDetail;
import com.phenix.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * OrderDetailRepository
 *
 * @author john
 * @since 2018-12-08
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 通过订单编号查找订单详情
     *
     * @param orderId 订单编号 {@link OrderMaster#getOrderId()}
     * @return 订单详情列表
     */
    List<OrderDetail> findByOrderId(String orderId);
}
