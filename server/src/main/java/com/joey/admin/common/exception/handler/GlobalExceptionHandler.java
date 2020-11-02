package com.joey.admin.common.exception.handler;

import com.joey.admin.common.exception.BaseException;
import com.joey.admin.common.exception.LoginFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joey
 * @create 2020-10-17 15:30
 * @desc ExceptionHandler
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException ex, HttpServletRequest request) {
        log.error("occur BaseException:" + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误!");
    }

    /**
     * 请求参数异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> errors = new HashMap<>(8);
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("occur MethodArgumentNotValidException:" + errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * 登录失败异常处理
     */
    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity handleLoginFailException(LoginFailException e,HttpServletRequest request){
        Map<String, Object> errors = new HashMap<>(8);
        String message = e.getMessage();
        log.error("occur LoginFailException:" + message);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }
}
