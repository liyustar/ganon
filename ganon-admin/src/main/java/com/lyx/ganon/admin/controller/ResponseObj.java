package com.lyx.ganon.admin.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @Description ResponseObj
 * @Author liyuxing
 * @Date 2019-12-08
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseObj<T> {

    private int code;
    private String msg;
    private T data;

    public static ResponseObj success() {
        return success(null);
    }

    public static <T> ResponseObj<T> success(T data) {
        return new ResponseObj(HttpStatus.OK.value(), "", data);
    }

    public static ResponseObj fail(String msg) {
        return new ResponseObj(HttpStatus.BAD_REQUEST.value(), msg, null);
    }
}
