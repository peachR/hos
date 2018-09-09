package com.peach.domain.util;

import com.peach.domain.enumeration.BaseEnum;

public class EnumUtil {
    private EnumUtil(){}

    public static <E extends Enum<?> & BaseEnum> E getEnumByCode(Class<E> type, int code) {
        E[] enumConstants = type.getEnumConstants();
        for(E e : enumConstants){
            if(e.getCode() == code){
                return e;
            }
        }
        return null;
    }
}
