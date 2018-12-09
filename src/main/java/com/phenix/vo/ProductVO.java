package com.phenix.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.phenix.entity.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ProductVO<T> {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private T foodList;

    public ProductVO<T> of(ProductCategory category) {
        BeanUtils.copyProperties(category, this);
        return this;
    }
}
