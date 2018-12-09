package com.phenix.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    SUCCESS(0, "成功"),
    FAILURE(1, "失败"),
    ERROR(2, "错误");

    private Integer code;
    private String description;
}
