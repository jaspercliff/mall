package com.jasper.common_utils;

import lombok.Data;
import lombok.Getter;

/**
 * @author com
 * @date 2023/5/4
 */
@Getter
public enum ResultCode {
    SUCCESS(2001,"The interface invoke is successful!!!"),
    COMMON_FAILED(2002,"The interface invoke is failed!!!"),
    VALIDATE_FAILED(2003,"validate parament failed!!!"),
    FORBIDDEN(2004,"no access!!!");
    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
