package com.gingu.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException{
    //异常状态码
    private String errCode;

    //错误信息
    private String errMessage;

    public BizException(BaseException baseException) {
        this.errCode = baseException.getErrCode();
        this.errMessage= baseException.getErrMessage();
    }
}
