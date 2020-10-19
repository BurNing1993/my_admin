package com.joey.admin.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Joey
 * @create 2020-10-15 19:43
 * @desc  如果用户名和密码正确，那么过滤器将创建一个JWT Token 并在HTTP Response 的header中返回它，格式：token: "Bearer +具体token值"
 **/
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String AUTH_LOGIN_URL = "/auth/login";

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl(AUTH_LOGIN_URL);
    }

}
