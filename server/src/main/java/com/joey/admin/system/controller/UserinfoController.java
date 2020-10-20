package com.joey.admin.system.controller;

import com.joey.admin.security.utils.JwtTokenUtil;
import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.enums.RoleNameEnum;
import com.joey.admin.system.service.UserService;
import com.joey.admin.system.vo.UserinfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Joey
 * @create 2020-10-20 11:31
 * @desc UserController
 **/
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {

  @Autowired
  private UserService userService;

  @GetMapping("")
  public ResponseEntity<UserinfoVO> userinfo(HttpServletRequest request) {
    String useId = JwtTokenUtil.getPayloadFromJwt(request);
    UserDO user = userService.getUserById(Long.parseLong(useId));
    UserinfoVO userinfoVO = new UserinfoVO();
    BeanUtils.copyProperties(user, userinfoVO);
    Set<String> roles = user.getRoles().stream().map(RoleDO::getRoleName).map(RoleNameEnum::getName).collect(Collectors.toSet());
    userinfoVO.setRoles(roles);
    return ResponseEntity.ok(userinfoVO);
  }
}
