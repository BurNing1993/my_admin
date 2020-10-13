package com.joey.admin.system.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author Joey
 * @create 2020-10-13 14:09
 * @desc JwtTokenProvider
 **/
@Component
@Slf4j
public class JwtTokenProvider {

    /**
     * 密匙
     */
    private final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    /**
     * 过期时间
     */
    @Value("${jwt.expirationInMs}")
    private int expirationInMs;

    public String generateToken(String payload) {
        /* UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal(); */
        Date now = new Date();
        System.out.println(expirationInMs);
        Date expiryDate = new Date(now.getTime() + expirationInMs);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(KEY)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        String userId = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody().getSubject();
        return Long.parseLong(userId);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            //don't trust the JWT!
            log.error("{} is Invalid!{}", token, e);
        }
        return false;
    }
}
