package com.peach.domain.annotation.log;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RuntimeLog {
    String description() default "";
}
