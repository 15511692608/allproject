package com.java.allproject.Interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2017/7/13.
 */
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.java.allproject.*.*ontroller..*(..))")
    public void point(){}

    @Around("point()")
    public Object pointInt(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println(123);
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        Set<Object> allParams = new LinkedHashSet<>(); //保存所有请求参数，用于输出到日志中
        LoginInterceptor.logger.info("请求开始，方法：{}", methodName);
        Object result = null;
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            //logger.debug("arg: {}", arg);
            if (arg instanceof Map<?, ?>) {
                //提取方法中的MAP参数，用于记录进日志中
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) arg;

                allParams.add(map);
            } else if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arg;

            }
        }
        try {
            if(result == null){
                // 一切正常的情况下，继续执行被拦截的方法
                result = proceedingJoinPoint.proceed();

                LoginInterceptor.logger.info("请求返回：{}", result);
            }
        } catch (Throwable e) {
            LoginInterceptor.logger.info("exception: {}", e);

        }
        return result;
    }

    @After("point()")
    public void After(){
        LoginInterceptor.logger.info("1");
    }
    @Before("point()")
    public void Before(){
        LoginInterceptor.logger.info("2");
    }
}
