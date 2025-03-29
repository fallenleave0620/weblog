
package com.quanxiaoha.weblog.common.aspect;

import com.quanxiaoha.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    @Pointcut("@annotation(com.quanxiaoha.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {
    }

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            Long startTime = System.currentTimeMillis();

            MDC.put("traceId", UUID.randomUUID().toString());
            String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();

            String methodName = proceedingJoinPoint.getSignature().getName();

            String description = getApiOperationLogDescription(proceedingJoinPoint);
            Object[] args = proceedingJoinPoint.getArgs();
            String argsJson = Arrays.stream(args).map(Object::toString).collect(Collectors.joining(","));

            // 打印请求相关参数
            log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                    description, argsJson, className, methodName);


            Object result = proceedingJoinPoint.proceed();

            Long executionTime = System.currentTimeMillis() - startTime;


//    String test =
            // 打印出参等相关信息
            log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                    description, executionTime, JsonUtil.toJsonString(result));


            return result;
        } finally {
            MDC.clear();
        }
//        return null;
    }


    private String getApiOperationLogDescription(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        Method method = methodSignature.getMethod();
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);
        return apiOperationLog.description();
    }

    private Function<Object, String> toJsonStr() {
        return arg -> JsonUtil.toJsonString(arg);
    }


}
