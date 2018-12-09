package com.phenix.util;

import com.phenix.enums.ResultCode;
import com.phenix.vo.ResultDataVO;
import com.phenix.vo.ResultVO;

/**
 * 返回结果工具类
 */
public class ResultUtils {

    public static ResultVO success(Object data) {
        ResultDataVO<Object> resultDataVO = new ResultDataVO<>();
        resultDataVO.setCode(ResultCode.SUCCESS.getCode());
        resultDataVO.setMessage(ResultCode.SUCCESS.getDescription());
        resultDataVO.setData(data);
        return resultDataVO;
    }

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultCode.SUCCESS.getCode());
        resultVO.setMessage(ResultCode.SUCCESS.getDescription());
        return resultVO;
    }

    public static ResultVO failure() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultCode.FAILURE.getCode());
        resultVO.setMessage(ResultCode.FAILURE.getDescription());
        return resultVO;
    }

    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

}
