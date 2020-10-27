package com.joey.admin.system.service.impl;

import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceImplTest {

  @Autowired
  private RoleService roleService;

  @Test
  void getRoleList() {
    List<RoleDO> roleList = roleService.getRoleList();
    System.out.println(roleList);
    Assertions.assertTrue(roleList.size()>0);
  }
}
