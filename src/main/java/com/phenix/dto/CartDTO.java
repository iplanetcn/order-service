package com.phenix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartDTO {
    /** 商品编号 */
    private String productId;

    /** 商品数量 */
    private Integer productQuantity;
}
