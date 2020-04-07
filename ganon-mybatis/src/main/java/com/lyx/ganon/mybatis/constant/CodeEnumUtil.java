package com.lyx.ganon.mybatis.constant;

import java.util.Arrays;

public class CodeEnumUtil {
    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        return Arrays.stream(enumConstants)
                .filter(e -> e.getCode() == code)
                .findFirst()
                .orElse(null);
    }
}
