package com.joey.admin.system.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * @author Joey
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
