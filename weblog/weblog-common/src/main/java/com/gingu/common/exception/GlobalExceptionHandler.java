package com.gingu.common.exception;

import com.gingu.common.enums.ResponseCodeEnum;
import com.gingu.common.util.Response;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@ControllerAdvice
//ControllerAdvice注解将被改注解标注的类中的方法可以应用于全部的控制器，即可以为所有的控制器增强功能，通常用作全局异常处理
@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
    * @ExceptionHandler注解指定一个或多个特定异常，当控制器请求发送指定的异常时被改注解拦截，执行被改注解标注的方法来处理异常。
    * 通常用做异常处理方法。
    * 写在控制器内部：只对该控制器生效。
      写在 @ControllerAdvice 类中：对所有控制器生效（全局异常处理）
      *异常处理方法可以接收多个参数，比如HttpServletRequest和该异常本身对象
      * @ResponseBody注解再将该异常处理方法的返回值作为控制器的返回值，所以需要与标准响应体做统一，统一响应格式
    * */
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

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    //参数校验错误异常处理方法
    public Response<Object> handleMethodArgumentNotVaildException(HttpServletRequest request, MethodArgumentNotValidException e){
        //状态码
        String errCode = ResponseCodeEnum.PRODUCT_NOT_FOUND.getErrCode();
        /*
        * MethodArgumentNotValidException 的 getBindingResult() 返回 BindingResult 对象，
        * 它封装了所有校验错误信息，包括字段错误（FieldError）和全局错误（ObjectError）
        * */
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        /*
        * Optional.ofNullable(value) 是一个工厂方法，用于创建可能包含空值的 Optional 对象。
        * 参数：任意类型，允许为 null。
            返回值：如果参数非 null，返回包含该值的 Optional；如果参数为 null，返回 Optional.empty()。
            主要用途：安全地包装可能为 null 的值，配合 ifPresent、orElse 等方法，以函数式风格避免显式 null 检查。
            *
            * ifPresent()方法用于对Optional非空对象的指定操作，具体操作看如何实现方法体
            * fieldError.getField()获取错误字段的属性名
            * fieldError.getDefaultMessage())获取该错误字段默认的提示信息
            * fieldError.getRejectedValue()获取该错误字段的错误值
        * */
//        Optional<List<FieldError>> fieldErrors = Optional.ofNullable(bindingResult.getFieldErrors());
        /*Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(new Consumer<List<FieldError>>() {
            @Override
            public void accept(List<FieldError> fieldErrors) {
                fieldErrors.forEach(new Consumer<FieldError>() {
                    @Override
                    public void accept(FieldError fieldError) {
                        stringBuilder.append(fieldError.getField()).append(" ")
                                .append(fieldError.getDefaultMessage()).append(",当前值:")
                                .append(fieldError.getRejectedValue())
                                .append(";");
                    }
                });
            }
        });*/
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent( fieldErrors ->{
                fieldErrors.forEach( fieldError ->{
                        stringBuilder.append(fieldError.getField())
                                .append(" ")
                                .append(fieldError.getDefaultMessage())
                                .append(",当前值:")
                                .append(fieldError.getRejectedValue())
                                .append(";");
                });
        });
        String errMessage = stringBuilder.toString();
        log.warn("{} request error,errorCode:{},errorMessage:{}",request.getRequestURI(),errCode,errMessage);
        return Response.fail(errCode,errMessage);
    }

}
