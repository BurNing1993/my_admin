package com.joey.admin.security.filter;

import com.joey.admin.security.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Joey
 * @create 2020-10-15 20:03
 * @desc JwtAuthorizationFilter
 **/
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    boolean hasToken = JwtTokenUtil.hasToken(request);
    if (hasToken) {
      JwtTokenUtil.validateToken(request);
    }
    filterChain.doFilter(request, response);
  }
}
