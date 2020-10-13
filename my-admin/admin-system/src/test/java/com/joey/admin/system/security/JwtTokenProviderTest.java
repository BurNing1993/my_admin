package com.joey.admin.system.security;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    @Test
    void generateToken() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = jwtTokenProvider.generateToken("1");
        System.out.println(token);
        Assert.hasLength(token,token+"is empty!");
    }

    @Test
    void getUserIdFromJWT() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        Long userId = 1L;
        String token = jwtTokenProvider.generateToken( Long.toString(userId));
        Long userIdFromJWT = jwtTokenProvider.getUserIdFromJWT(token);
        System.out.println(userIdFromJWT);
        Assert.isTrue(userId.equals(userIdFromJWT),"获取UserId失败!");
    }

    @Test
    void validateToken() {
    }
}