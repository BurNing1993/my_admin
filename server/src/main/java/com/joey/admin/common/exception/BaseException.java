package com.joey.admin.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Joey
 * @create 2020-10-14 10:20
 * @desc 基础Exception
 **/
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends RuntimeException{
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}