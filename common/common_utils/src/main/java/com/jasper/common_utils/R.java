package com.jasper.common_utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author com
 * @date 2023/5/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;
    public static <T> R<T> success(T data) {
        return new R<>(true,
                ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage(),
                data);
    }
    public static <T> R<T> success() {
        return new R<>(true,
                ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage(),
                null);
    }

    public static <T> R<T> success(String message, T data) {
        return new R<T>(true,
                ResultCode.SUCCESS.getCode(),
                message,
                data);
    }

    public static <T> R<T> fail() {
        return new R<>(false,
                ResultCode.COMMON_FAILED.getCode(),
                ResultCode.COMMON_FAILED.getMessage(),
                null);
    }
    public static <T> R<T> fail(String message) {
        return new <T>R<T>(false,
                ResultCode.COMMON_FAILED.getCode(),
                message,
                null);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return new <T>R<T>(false,
                code,
                message,
                null);
    }


}
