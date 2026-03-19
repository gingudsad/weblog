package com.gingu.common.exception;

import com.gingu.common.enums.ResponseCodeEnum;
import com.gingu.common.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({BizException.class})
    public Response<Object> handleBizException(HttpServletRequest request,BizException  e){
        log.warn("{} request fail, errorCode:{}",request.getRequestURI(),e.getErrCode());
        return Response.fail(e);
    }


    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Response<Object> handleOtherException(HttpServletRequest request,Exception  e){
        log.error("{} request fail",request.getRequestURI(),e);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }

}
