package com.phenix.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phenix.entity.ProductInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    public ProductInfoVO of(ProductInfo productInfo) {
        BeanUtils.copyProperties(productInfo, this);
        return this;
    }
}
