package com.gingu.common.enums;

import com.gingu.common.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//定义一个枚举类 ，属性包括errCode和errMessage，有全参构造方法和geter方法，不能有seter方法
public enum ResponseCodeEnum implements BaseException {
    /*
    * SYSTEM_ERROR和PRODUCT_NOT_FOUND是定义的全局唯一的常量，是ResponseCodeEnum的实例对象。
    * 在枚举类开头定义，以 ,逗号  分割，通常大写，不可变。
    * ResponseCodeEnum implements BaseException 实现BaseException类需要重写它的getErrCode和getErrMessage方法，
    * 但是这俩方法名和errCode、errMessage的geter方法重名，所有不需要手动书写，lombok的@Getter注解来隐式书写。
    * */

    /*
    * SYSTEM_ERROR作为系统通用异常，用来提示用户不知名的异常
    * PRODUCT_NOT_FOUND是业务异常
    * */

    //通用异常状态码
    SYSTEM_ERROR("1000","出错啦，正在努力修复中"),

    //业务异常状态码
    PRODUCT_NOT_FOUND("2000","该产品不存在(测试使用)"),

    PARAM_NOT_VALID("10001","参数错误");
    ;

    //异常码
    public String errCode;

    //错误信息
    public String errMessage;

}
