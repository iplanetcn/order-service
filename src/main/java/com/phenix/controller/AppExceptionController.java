package com.phenix.controller;

import com.phenix.enums.Result;
import com.phenix.exception.SellException;
import com.phenix.util.ResultUtils;
import com.phenix.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AppExceptionController {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e) {
        if (e instanceof SellException) {
            SellException sellException = (SellException) e;
            return ResultUtils.error(sellException);
        } else {
            return ResultUtils.error(Result.UNKNOWN_ERROR);
        }
    }
}
