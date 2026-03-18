package com.gingu.common.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //设置保留规则 RUNTIME表示运行时保留
@Target(ElementType.METHOD) //设置注解生效的位置 METHOD表示只能用在方法上
@Documented
//使用@interface关键字声明一个自定义注解
public @interface ApiOperationLog {


    String description() default "";
}
