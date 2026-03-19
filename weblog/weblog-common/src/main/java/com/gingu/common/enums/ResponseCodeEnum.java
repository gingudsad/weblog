package com.gingu.common.enums;

import com.gingu.common.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCodeEnum implements BaseException {
    //通用异常状态码
    SYSTEM_ERROR("1000","出错啦，正在努力修复中"),

    //业务异常状态码
    PRODUCT_NOT_FOUND("2000","该产品不存在(测试使用)"),
    ;

    //异常码
    public String errCode;

    //错误信息
    public String errMessage;

}
