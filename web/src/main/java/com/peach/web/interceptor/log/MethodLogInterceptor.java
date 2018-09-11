package com.peach.web.interceptor.log;

import com.peach.domain.annotation.log.RuntimeLog;
import com.peach.domain.util.AnnotationUtil;
import com.peach.domain.util.AopUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
public class MethodLogInterceptor {
    private static final Logger logger = LogManager.getLogger(MethodLogInterceptor.class);

    @Around("@annotation(com.peach.domain.annotation.log.RuntimeLog)")
    public Object handler(ProceedingJoinPoint pjp) throws Throwable {
        try{
            long begTime = System.currentTimeMillis();
            Object result = pjp.proceed();
            long during = System.currentTimeMillis() - begTime;
            String description = AnnotationUtil.getAnnotationFromMethod(AopUtil.getTargetMethod(pjp), RuntimeLog.class).description();
            logger.warn(getMethodName(pjp) + ": " + during + "ms; " + description);
            return result;
        }catch (Throwable ex){
            logger.error("MethodLogInterceptor exception:", ex);
            throw ex;
        }
    }

    private String getMethodName(JoinPoint pjp) {
        String clazzName = AopUtil.getTargetClass(pjp).getName();
        String methodName = AopUtil.getTargetMethod(pjp).getName();
        Object[] args = AopUtil.getTargetMethodArgs(pjp);
        StringBuilder methodStringBuilder = new StringBuilder();
        methodStringBuilder.append(clazzName)
                .append(".")
                .append(methodName)
                .append("(");
        for(Object arg : args){
            methodStringBuilder.append(arg.getClass().getSimpleName())
                    .append(", ");
        }
        return methodStringBuilder.delete(methodStringBuilder.length() - 2, methodStringBuilder.length())
                .append(")")
                .toString();
    }
}
