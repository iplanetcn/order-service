package com.phenix.exception;

import com.phenix.enums.Result;
import lombok.Getter;

@Getter
public class SellException extends RuntimeException {
    private final Integer code;

    public SellException(Result result) {
        super(result.getMessage());
        this.code = result.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
