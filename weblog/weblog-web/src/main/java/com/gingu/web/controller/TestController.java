package com.gingu.web.controller;

import com.gingu.common.aspect.ApiOperationLog;
import com.gingu.common.enums.ResponseCodeEnum;
import com.gingu.common.exception.BizException;
import com.gingu.common.util.JsonUtil;
import com.gingu.common.util.Response;
import com.gingu.web.model.USer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
@Api(tags = "测试接口")
public class TestController {
    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口", notes = "用于测试的接口")
//    public Response test(@RequestBody @Validated USer user, BindingResult bindingResult){
    public Response test(@RequestBody @Validated USer user){
        /*int i=1/0;
        return Response.success();*/
//        throw new BizException(ResponseCodeEnum.SYSTEM_ERROR);
        /*if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            *//*String errorMessage = bindingResult.getFieldErrors().stream().map(new Function<FieldError, String>() {
                @Override
                public String apply(FieldError fieldError) {
                    String defaultMessage = fieldError.getDefaultMessage();
                    return defaultMessage;
                }
            }).collect(Collectors.joining(","));*//*
            return Response.fail(errorMessage);
        }*/
//        return Response.success("参数没有任何问题");
//        return Response.success();


        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());

        return Response.success(user);






    }
}
