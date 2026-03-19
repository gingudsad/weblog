package com.gingu.common.exception;

public interface BaseException {

    //获取异常状态码
    String getErrCode();

    //获取异常信息
    String getErrMessage();
}
