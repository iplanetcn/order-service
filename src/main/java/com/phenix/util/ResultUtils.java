package com.phenix.util;

import com.phenix.enums.Result;
import com.phenix.exception.SellException;
import com.phenix.vo.ResultDataVO;
import com.phenix.vo.ResultVO;

/**
 * 返回结果工具类
 */
public class ResultUtils {

    public static <T> ResultDataVO<T> success(T data) {
        ResultDataVO<T> resultDataVO = new ResultDataVO<>();
        resultDataVO.setCode(Result.SUCCESS.getCode());
        resultDataVO.setMessage(Result.SUCCESS.getMessage());
        resultDataVO.setData(data);
        return resultDataVO;
    }

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(Result.SUCCESS.getCode());
        resultVO.setMessage(Result.SUCCESS.getMessage());
        return resultVO;
    }

    public static ResultVO failure() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(Result.FAILURE.getCode());
        resultVO.setMessage(Result.FAILURE.getMessage());
        return resultVO;
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static ResultVO error(SellException e) {
        return error(e.getCode(), e.getMessage());
    }

    public static ResultVO error(Result r) {
        return error(r.getCode(), r.getMessage());
    }




}
