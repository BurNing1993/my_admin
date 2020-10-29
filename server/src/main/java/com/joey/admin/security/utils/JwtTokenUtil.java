package com.joey.admin.security.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Joey
 * @create 2020-10-14 11:07
 * @desc JwtTokenUtil
 **/
@Slf4j
public class JwtTokenUtil {

  /**
   * Header
   */
  private static final String HEADER_KEY = "Authorization";
  /**
   * 令牌前缀
   */
  private static final String TOKEN_PREFIX = "Bearer ";
  /**
   * 密匙
   */
  private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  /**
   * 过期时间 6小时
   */
  private static final int EXPIRATION_IN_MS = 6 * 60 * 60 * 1000;

  public static String generateToken(String payload) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + EXPIRATION_IN_MS);

    return Jwts.builder()
      .setSubject(payload)
      .setIssuedAt(new Date())
      .setExpiration(expiryDate)
      .signWith(KEY)
      .compact();
  }


  public static String getPayloadFromJwt(String token) {
    String payload = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody().getSubject();
    return payload;
  }

  public static String getPayloadFromJwt(HttpServletRequest request) {
    String token = getToken(request);
    String payload = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody().getSubject();
    return payload;
  }


  public static boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      //don't trust the JWT!
      log.error("{} is Invalid!{}", token, e);
    }
    return false;
  }

  /**
   * 获取请求token
   *
   * @param request
   * @return token
   */
  private static String getToken(HttpServletRequest request) {
    String token = request.getHeader(HEADER_KEY);
    if (StringUtils.hasText(token) && StringUtils.containsWhitespace(TOKEN_PREFIX)) {
      return token.substring(TOKEN_PREFIX.length());
    } else {
      throw new JwtException("Invalid Token!");
    }
  }
  /**
   *  token是否存在
   *
   * @param request
   * @return token
   */
  public static boolean hasToken(HttpServletRequest request) {
    String token = request.getHeader(HEADER_KEY);
    if (StringUtils.hasText(token) && StringUtils.containsWhitespace(TOKEN_PREFIX)) {
      return true;
    }
    return false;
  }


    /**
     * 验证Token
     *
     * @param request
     * @return
     */
  public static boolean validateToken(HttpServletRequest request) {
    try {
      String token = getToken(request);
      Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      //don't trust the request!
      log.error("Invalid Token!", request, e);
    }
    return false;
  }
}
