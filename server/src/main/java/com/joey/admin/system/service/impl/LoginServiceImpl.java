package com.joey.admin.system.service.impl;

import com.joey.admin.common.exception.LoginFailException;
import com.joey.admin.security.entity.JwtUser;
import com.joey.admin.security.service.impl.UserDetailsServiceImpl;
import com.joey.admin.security.utils.JwtTokenUtil;
import com.joey.admin.system.request.LoginRequest;
import com.joey.admin.system.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Joey
 * @create 2020-10-19 10:20
 * @desc LoginServiceImpl
 **/
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            );
            Authentication authenticate = authenticationManager.authenticate(
                usernamePasswordAuthenticationToken
            );
            log.info("Authenticate:" + String.valueOf(authenticate));
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                JwtUser user = (JwtUser) authenticate.getPrincipal();
                String token = JwtTokenUtil.generateToken(String.valueOf(user.getId()));
                return token;
            } else {
                log.error("Login Fail:" + loginRequest.toString());
                throw new LoginFailException("账号或密码错误！", loginRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Login Fail:" + loginRequest.toString());
            throw new LoginFailException("账号或密码错误！", loginRequest, e);
        }
    }
}
