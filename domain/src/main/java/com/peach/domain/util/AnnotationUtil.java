package com.peach.domain.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtil {
    public static <T extends Annotation> T getAnnotationFromMethod(Method method, Class<T> clazz){
        return method.getAnnotation(clazz);
    }
}
