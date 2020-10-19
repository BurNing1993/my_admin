package com.joey.admin.system.service;

import com.joey.admin.system.request.LoginRequest;

/**
 * @author Joey
 * @create 2020-10-19 10:19
 * @desc LoginService
 **/
public interface LoginService {

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    String login(LoginRequest loginRequest);
}
