package com.joey.admin.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Joey
 * @create 2020-11-02 10:20
 * @desc 登录失败Exception
 **/
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailException extends RuntimeException {
    private Object loginRequest;

    public LoginFailException(String message, Object loginRequest) {
        super(message);
        this.loginRequest = loginRequest;
    }

    public LoginFailException(String message, Object loginRequest, Throwable cause) {
        super(message, cause);
        this.loginRequest = loginRequest;
    }
}
