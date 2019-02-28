package com.phenix.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.phenix.util.serializer.DateToLongSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class ProductInfo {
    /** 商品编号 */
    @Id
    private String productId;

    /** 商品名称 */
    @NonNull
    private String productName;

    /** 商品价格 */
    @NonNull
    private BigDecimal productPrice;

    /** 商品库存 */
    @NonNull
    private Integer productStack;

    /** 商品描述 */
    @NonNull
    private String productDescription;

    /** 商品图标 */
    @NonNull
    private String productIcon;

    /** 商品状态（0正常，1下架） */
    @NonNull
    private Integer productStatus;

    /** 类目编号 */
    @NonNull
    private Integer categoryType;

    /** 创建时间 */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /** 更新时间 */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;
}
