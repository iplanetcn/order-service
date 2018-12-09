package com.phenix.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    UP(0, "正常"),
    DOWN(1, "下线");

    private Integer code;
    private String description;
}
