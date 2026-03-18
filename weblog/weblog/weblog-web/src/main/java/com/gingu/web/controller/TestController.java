package com.gingu.web.controller;

import com.gingu.common.aspect.ApiOperationLog;
import com.gingu.web.model.USer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public USer test(@RequestBody USer user){
        return user;
    }

}
