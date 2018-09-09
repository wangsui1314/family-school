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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAspect {


//    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /*
     * 在执行上面其他操作的同时也执行这个方法
     * */
    //controller层切入点
    @Pointcut("@annotation(com.bjwk.utils.annotation.MyLog)")
    public void controllerAspect() {

    }


    @Around("controllerAspect()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.info("调用开始--> {}.{} : 请求参数:{} ", method.getDeclaringClass().getName(), method.getName(), StringUtils.join(args, " ; "));
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;

        log.info("调用结束--> 返回值:{} 耗时:{}ms", JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue), timeConsuming);
        return result;
    }

}
