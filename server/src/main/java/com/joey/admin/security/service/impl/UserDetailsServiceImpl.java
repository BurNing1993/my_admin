package com.joey.admin.security.service.impl;

import com.joey.admin.security.entity.JwtUser;
import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Joey
 * @create 2020-10-15 11:33
 * @desc UserDetailsService实现类
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userService.getUserByUsername(username);
        return JwtUser.create(user);
    }
}
