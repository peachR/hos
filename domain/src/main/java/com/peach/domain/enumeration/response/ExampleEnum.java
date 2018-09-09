package com.peach.domain.enumeration.response;

import com.peach.domain.enumeration.BaseEnum;

public enum ExampleEnum implements BaseEnum {
    START(0, "begin to operation"),
    END(1, "end all");

    private int code;
    private String msg;

    ExampleEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
