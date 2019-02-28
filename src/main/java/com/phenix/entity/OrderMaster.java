package com.phenix.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.phenix.enums.OrderStatus;
import com.phenix.enums.PayStatus;
import com.phenix.util.serializer.DateToLongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /** 订单ID */
    @Id
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
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /** 修改时间 */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

}
