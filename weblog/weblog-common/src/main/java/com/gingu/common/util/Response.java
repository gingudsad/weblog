package com.gingu.common.util;
import java.io.Serializable;

import com.gingu.common.exception.BaseException;
import com.gingu.common.exception.BizException;
import lombok.Data;

@Data
//全局响应类
public class Response<T> {
    //是否请求成功
    private Boolean success = true;
    //响应消息
    private String message;
    //状态码
    private String errorCode;
    //响应数据
    private T data;

    // ==========  成功响应 ======
    public static <T> Response<T> success(){
        Response<T> response = new Response<>();
        return response;
    }


    public static <T>Response<T> success(T data){
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    //=========== 失败响应 =========
    public static <T>Response<T> fail(){
        Response<T> response = new Response<T>();
        response.setSuccess(false);
        return response;
    }

    public static <T>Response<T> fail(String errMessage){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errMessage);
        return response;
    }

    public static <T>Response<T> fail(String errMessage,String errCode){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errMessage);
        response.setErrorCode(errCode);
        return response;
    }


    public static <T>Response<T> fail(BizException bizException){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrCode());
        response.setMessage(bizException.getErrMessage());
        return response;
    }

    public static <T>Response<T> fail(BaseException baseException){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(baseException.getErrCode());
        response.setMessage(baseException.getErrMessage());
        return response;
    }

}
