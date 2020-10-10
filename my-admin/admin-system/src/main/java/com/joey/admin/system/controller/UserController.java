package com.joey.admin.system.controller;

import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joey
 * @create 2020-10-10 18:27
 * @desc UserController
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public Page<UserDO> getUserPage(){
        return userService.getUserPage(null,null);
    }
}
