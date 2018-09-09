package com.peach.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enumeration.response.ResponseEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> {
    private T data;
    private boolean success;
    private String subcode;
    private String msg;
    private ResponseEnum responseCode;

    private Result(){
        this.msg = "";
    }

    public static <T> Result<T> newFailResult(){
        Result<T> result = new Result<T>();
        result.setSubcode("-1");
        result.setSuccess(false);
        result.setResponseCode(ResponseEnum.UNKNOW_ERROR);
        return result;
    }

    public static <T> Result<T> newSuccessResult(T data){
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setResponseCode(ResponseEnum.SUCCESS);
        result.setSuccess(true);
        result.setSubcode("0");
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseEnum getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseEnum responseCode) {
        this.responseCode = responseCode;
    }
}
