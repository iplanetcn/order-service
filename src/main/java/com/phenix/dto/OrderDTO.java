package com.phenix.dto;

import com.phenix.entity.OrderDetail;
import com.phenix.enums.OrderStatus;
import com.phenix.enums.PayStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    /** 订单ID */
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为新订单 */
    private Integer orderStatus = OrderStatus.NEW.getCode();

    /** 支付状态, 默认为未支付 */
    private Integer payStatus = PayStatus.WAIT.getCode();

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 订单详情列表 */
    List<OrderDetail> orderDetailList;
}
