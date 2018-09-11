package com.bjwk.utils.annotation;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAspect {


    @Autowired
    private TokenValidateAspect tokenValidateAspect;


    //controller层切入点
    @Pointcut("@annotation(com.bjwk.utils.annotation.MyLog)")
    public void controllerAspect() {

    }


    @Around("controllerAspect()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //参数
        Object[] args = joinPoint.getArgs();

        Map<String, Object> map = tokenValidateAspect.getFieldsName(this.getClass(), clazzName, methodName, args);


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("调用开始--> {}.{} : 请求参数:{} ", method.getDeclaringClass().getName(), method.getName(), map);
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;

        log.info("调用结束--> 返回值:{} 耗时:{}ms", JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue), timeConsuming);
        return result;
    }

}
