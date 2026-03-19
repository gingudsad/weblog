package com.gingu.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException{
    /*
    * 业务异常类继承RuntimeException，异常属性和基础异常类保持一致，
    * 并使用基础异常类对象作为参数构建构造方法来创建对象。
    * 目的是为了在抛出创建业务异常BizException对象的时候可以使用实现了基础异常BaseException接口的枚举类的实例SYSTEM_ERROR
    * 来创建BizException对象。
    * 例如： throw new BizException(ResponseCodeEnum.SYSTEM_ERROR);
    * SYSTEM_ERROR就是基础异常BaseException接口的实现类对象实例，且是常量不可改变。
    * SYSTEM_ERROR("1000","出错啦，正在努力修复中")。
    * 使用SYSTEM_ERROR的属性值 errCode="1000" 和  errMessage="出错啦，正在努力修复中" 传递给BizException的构造方法
    * 以此来创建BizException类对象。
    * */
    //异常状态码
    private String errCode;

    //错误信息
    private String errMessage;

    public BizException(BaseException baseException) {
        this.errCode = baseException.getErrCode();
        this.errMessage= baseException.getErrMessage();
    }
}
