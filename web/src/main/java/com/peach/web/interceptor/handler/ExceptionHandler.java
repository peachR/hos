package com.peach.web.interceptor.handler;

import com.peach.domain.response.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public <T> Result<T> handleSysException(Exception ex){
        logger.error("ExceptionHandler exception: ", ex);
        return Result.newFailResult();
    }
}
