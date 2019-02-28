package com.phenix.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.phenix.util.serializer.DateToLongSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 类目
 */
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class ProductCategory {
    /** 类目ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    /** 类目名称 */
    @NonNull
    private String categoryName;

    /** 类目类型 */
    @NonNull
    private Integer categoryType;

    /** 创建时间 */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /** 更新时间 */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;
}
