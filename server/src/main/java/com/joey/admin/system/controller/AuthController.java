package com.joey.admin.system.controller;

import com.joey.admin.security.entity.JwtUser;
import com.joey.admin.security.utils.JwtTokenUtil;
import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.enums.RoleNameEnum;
import com.joey.admin.system.request.LoginRequest;
import com.joey.admin.system.request.SignupRequest;
import com.joey.admin.system.service.LoginService;
import com.joey.admin.system.service.RoleService;
import com.joey.admin.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Joey
 * @create 2020-10-17 15:12
 * @desc 登录 注册 相关
 **/
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("LoginRequest:"+loginRequest.toString());
        String token = loginService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("用户名已存在!");
        }
        UserDO userDO = new UserDO();
        // 设置ID,否则报错
        userDO.setId(0L);
        userDO.setUsername(signupRequest.getUsername());
        userDO.setNickname(signupRequest.getUsername());
        userDO.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userDO.setHasDeleted(false);
        userDO.setEnabled(true);
        Set<RoleDO> roles = new HashSet<>();
        RoleDO role = roleService.findByRoleName(RoleNameEnum.USER);
        roles.add(role);
        userDO.setRoles(roles);
        userService.saveUser(userDO);
        return ResponseEntity.ok("注册成功!");
    }
}
