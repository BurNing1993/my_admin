package com.joey.admin.security.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {

    private String payload = "1";

    @Test
    void generateToken() {
        String token = JwtTokenUtil.generateToken(payload);
        System.out.println(token);
        Assertions.assertTrue(token.length()>0);
    }

    @Test
    void getPayloadFromJwt() {
        String token = JwtTokenUtil.generateToken(payload);
        System.out.println(token);
        String payloadFormJwt = JwtTokenUtil.getPayloadFromJwt(token);
        Assertions.assertTrue(payload.equals(payloadFormJwt));
    }

    @Test
    void validateToken() {
        String token = JwtTokenUtil.generateToken(payload);
        System.out.println(token);
        Assertions.assertTrue(JwtTokenUtil.validateToken(token));
    }
}