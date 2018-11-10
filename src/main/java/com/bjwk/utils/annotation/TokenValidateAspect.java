package com.bjwk.utils.annotation;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.utils.*;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserTokenValidateAspect s
 * @Description: TODO(" 验证用户token ")
 * @author: lihui
 * @date: 2018年4月17日 下午11:57:30
 */
@Aspect
@Component
@Slf4j
public class TokenValidateAspect {

    //token验证层controller切入点
    @Pointcut(value = "@annotation(com.bjwk.utils.annotation.TokenValidate)")
    public void tokenAspect() {

    }

    /**
     * 验证token
     *
     * @param joinpoint
     * @throws Throwable
     */
    @Around("tokenAspect()")
    public Object around(ProceedingJoinPoint joinpoint) throws Throwable {
        Jedis jedis = RedisClient.getJedis();
        DataWrapper dataWrapper = new DataWrapper();
        try {
            MethodSignature signature = (MethodSignature) joinpoint.getSignature();
            Method method = signature.getMethod();
            String methodName = method.getName();
            String classType = joinpoint.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            //String methodName = signature.getName(); //获取方法名称
            Object[] args = joinpoint.getArgs();//参数
            //获取参数名称和值
            Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName, args);

            String token = (String) nameAndArgs.get("token");

            /**
             * 1.验证该用户是否已登录，通过是否包含此token来判断
             */
            Object obj = null;
            if (token == null){
                log.info("无需检查权限接口...--> {}.{} : token:{}。默认通过！", method.getDeclaringClass().getName(), methodName, nameAndArgs.get("token"));
                //放行
                obj = joinpoint.proceed();
                return obj;
            }
            String userName = jedis.hget("loginStatus", token);
            if (userName != null) {
                log.info("用户权限检查结果通知...--> {}.{} : token:{}。通过！", method.getDeclaringClass().getName(), methodName, nameAndArgs.get("token"));
                //放行
                obj = joinpoint.proceed();
                return obj;
            } else {
                dataWrapper.setCallStatus(CallStatusEnum.FAILED);
                dataWrapper.setMsg("用户权限检查结果通知:失败");
                log.error("用户权限检查结果通知...--> {}.{} : token:{}。失败！", method.getDeclaringClass().getName(), methodName, nameAndArgs.get("token"));
                return dataWrapper;
            }
        } catch (Exception e) {
            e.printStackTrace();
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(e.getMessage());
            dataWrapper.setMsg("系统异常，请稍后在试");
        } finally {
            jedis.close();
        }
        return dataWrapper;
    }

    /**
     * 获取方法参数
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws ChangeSetPersister.NotFoundException
     */
    public Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws ChangeSetPersister.NotFoundException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            ClassPool pool = ClassPool.getDefault();
            //ClassClassPath classPath = new ClassClassPath(this.getClass());
            ClassClassPath classPath = new ClassClassPath(cls);
            pool.insertClassPath(classPath);

            CtClass cc = null;

            cc = pool.get(clazzName);
            CtMethod cm = cc.getDeclaredMethod(methodName);
            MethodInfo methodInfo = cm.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr == null) {
                throw new RuntimeException();
            }
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < cm.getParameterTypes().length; i++) {
                //paramNames即参数名
                map.put(attr.variableName(i + pos), args[i]);
            }

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
