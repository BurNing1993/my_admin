package com.joey.admin.system.controller;

import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Joey
 * @create 2020-10-27 10:06
 * @desc RoleController
 **/
@RestController
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping("/roles")
  public ResponseEntity<?> getRoleList() {
    List<RoleDO> roleList = roleService.getRoleList();
    return ResponseEntity.ok(roleList);
  }
}
