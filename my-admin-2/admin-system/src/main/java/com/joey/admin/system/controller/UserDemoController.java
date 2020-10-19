package com.joey.admin.system.controller;

import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Joey
 * @create 2020-10-10 18:27
 * @desc UserController
 **/
@Component
@RestController
public class UserDemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/page")
    public Page<UserDO> getUserPage(@RequestParam(value = "nickname", required = false, defaultValue = "") String nickname,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        UserDO userDO = new UserDO();
        userDO.setNickname(nickname);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "userId");
        return userService.getUserPage(userDO, pageable);
    }

    @PostMapping("/user")
    public UserDO addUser(@RequestBody UserDO userDO){
        return userService.saveUser(userDO);
    }

    @PutMapping("/user")
    public UserDO updateUser(@RequestBody UserDO userDO){
        return userService.saveUser(userDO);
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable Long userId, HttpServletResponse response){
        UserDO userDO  = userService.getUser(userId);
        if (userDO == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return  "FAIL";
        }
        userDO.setHasDeleted(2);
        userService.saveUser(userDO);
        return "OK";
    }
}
