package com.gingu.common.aspect;

import com.gingu.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Aspect //定义通知类
@Component
public class ApiOperationLogAspect {
    //定义切点
    /*@annotation(com.gingu.common.aspect.ApiOperationLog)
    切点表达式，匹配被括号内指定的注解标注的方法
    *即被com.gingu.common.aspect.ApiOperationLog注解标注的方法即为匹配的切点
    * */
    @Pointcut("@annotation(com.gingu.common.aspect.ApiOperationLog)")
    public void apiOperationLog(){};

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            long startTime=System.currentTimeMillis();
            MDC.put("traceId", UUID.randomUUID().toString());
//            Object target = proceedingJoinPoint.getTarget();
//            Object aClass = proceedingJoinPoint.getTarget().getClass();
            String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
//            Signature signature = proceedingJoinPoint.getSignature();
            String methodName = proceedingJoinPoint.getSignature().getName();
            Object[] args = proceedingJoinPoint.getArgs();//args是注解标注的方法的参数组成的对象数组
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));
            String description= getApiOperationLogDescription(proceedingJoinPoint);

            //target 是  com.gingu.web.controller.TestController@6484b8d2
//            log.info("====proceedingJoinPoint.getTarget():{}==",target);

            //aClass 是 class com.gingu.web.controller.TestController
//            log.info("====proceedingJoinPoint.getTarget().getClass():{}==",aClass);

            //signature 是 USer com.gingu.web.controller.TestController.test(USer)
//            log.info("====proceedingJoinPoint.getSignature():{}==",signature);

            //args 是 User(name=jack, sex=2)
//            log.info("===args:{}==",args);
//            log.info("===args:{}==",args.length);
//            log.info("===args:{}==",args[0]);

            log.info("=====请求开始:[{}],入参:{},请求类:{},请求方法:{}===========",
                    description,argsJsonStr,className,methodName);

            Object result = proceedingJoinPoint.proceed();

            long executionTime = System.currentTimeMillis() - startTime;

            log.info("=====请求结束:[{}],耗时:{}ms,出参:{}======",
                    description,executionTime, JsonUtil.toJsonString(result));
            return result;
        } finally {
            MDC.clear();
        }
    }

    /*private Function<Object,String> toJsonStr(){
        return new Function<Object, String>() {
            @Override
            public String apply(Object args) {
                return JsonUtil.toJsonString(args);
            }
        };
    }*/
    private Function<Object,String> toJsonStr(){
        return  args -> JsonUtil.toJsonString(args);
    }

    private String getApiOperationLogDescription(ProceedingJoinPoint proceedingJoinPoint){
        /*
        * 从 ProceedingJoinPoint 中获取方法签名，强制转换为 MethodSignature。
        通过反射获得当前方法对象，然后获取方法上的 @ApiOperationLog 注解。
        最后返回注解的 description() 值。
        * */
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
//        System.out.println(signature); //USer com.gingu.web.controller.TestController.test(USer)
        Method method = signature.getMethod();
//        System.out.println(method);//public com.gingu.web.model.USer com.gingu.web.controller.TestController.test(com.gingu.web.model.USer)
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);
//        System.out.println(apiOperationLog);//@com.gingu.common.aspect.ApiOperationLog(description="\u6d4b\u8bd5\u63a5\u53e3")
        return apiOperationLog.description();
    }

}
