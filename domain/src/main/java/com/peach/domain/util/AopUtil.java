package com.peach.domain.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

/**
 * @author peach
 * @date 2018-09-11 18:48:08
 * aop工具类
 */
public class AopUtil {
    private AopUtil(){}

    public static Class<?> getTargetClass(JoinPoint jp){
        return jp.getTarget().getClass();
    }

    public static Method getTargetMethod(JoinPoint jp){
        Signature signature =  jp.getSignature();
        if(signature instanceof MethodSignature){
            MethodSignature methodSignature = (MethodSignature)signature;
            return methodSignature.getMethod();
        }
        throw new NoSuchElementException("AopUtil.getTargetMethod can not find target method");
    }

    public static Object[] getTargetMethodArgs(JoinPoint jp){
        Object[] args = jp.getArgs();
        return args;
    }
}
