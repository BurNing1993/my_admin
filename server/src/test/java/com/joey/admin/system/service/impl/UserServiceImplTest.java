package com.joey.admin.system.service.impl;

import com.joey.admin.common.exception.ResourceNotFoundException;
import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.service.RoleService;
import com.joey.admin.system.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    void getUserPage() {
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "id");
        Page<UserDO> userPage = userService.getUserPage(null, pageable);
        Assertions.assertTrue(userPage.getNumber() > 0);
    }

    @Test
    void saveUser() {
        UserDO user = new UserDO();
        user.setId(0l); // 设置ID(任意) ,否则报错
        user.setNickname("joey123");
        user.setUsername("joey123");
        user.setPassword("21asee23eqwrqewqerq23");
        user.setEnabled(true);
        user.setHasDeleted(false);
        List<RoleDO> list = roleService.findAllByIdIn(Arrays.asList(1l,2l));
        user.setRoles(new HashSet<RoleDO>(list));
        UserDO userDO = userService.saveUser(user);
        System.out.println(userDO);
        Assertions.assertTrue(userDO.getId() != null);
    }

    @Test
    void getUserById() {
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.getUserById(0L);
        });
        Assertions.assertTrue(exception.getMessage().contains("'0'"));
    }

    @Test
    void getUserByUsername() {
        UserDO userDO = userService.getUserByUsername("admin");
        Assertions.assertTrue(userDO!=null);
    }

    @Test
    void testGetUserPage() {
    }

    @Test
    void testSaveUser() {
    }

    @Test
    void testGetUserById() {
    }

    @Test
    void testGetUserByUsername() {
    }
}
