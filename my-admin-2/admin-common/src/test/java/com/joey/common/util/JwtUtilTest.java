//package com.joey.common.util;
//
//
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtUtilTest {
//
//    @Test
//    public void testGetToken() throws Exception {
//        Map<String, String> payload = new HashMap<>();
//        payload.put("userId", "1");
//        payload.put("userName", "admin");
//
//        String token = JwtUtil.getToken(payload);
//        System.out.println(token);
//        Assert.assertNotEquals("", token);
//    }
//
//    @Test
//    public void testVerify() throws Exception {
//        Map<String, String> payload = new HashMap<>();
//        payload.put("userId", "1");
//        payload.put("userName", "admin");
//        String token = JwtUtil.getToken(payload);
//        System.out.println(token);
//        //解析token
//        DecodedJWT verify = JwtUtil.verify(token);
//        System.out.println("userId:" + verify.getClaim("userId").asString());
//        System.out.println("userName:" + verify.getClaim("userName").asString());
//        Assert.assertEquals(verify.getClaim("userId").asString(), "1");
//    }
//}