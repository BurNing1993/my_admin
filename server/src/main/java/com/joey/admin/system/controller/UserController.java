package com.joey.admin.system.controller;

import com.joey.admin.system.constants.UserConstants;
import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.request.UserPageRequest;
import com.joey.admin.system.request.UserRequest;
import com.joey.admin.system.service.RoleService;
import com.joey.admin.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author Joey
 * @create 2020-10-20 16:14
 * @desc User
 **/
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/page")
    public ResponseEntity<?> getUserPage(UserPageRequest userPageRequest) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userPageRequest, userDO);
        Pageable page = PageRequest.of(userPageRequest.getPage() - 1, userPageRequest.getSize(), Sort.Direction.DESC, "id");
        Page<UserDO> userPage = userService.getUserPage(userDO, page);
        log.info("UserPageRequest:", userPageRequest.toString());
        return ResponseEntity.ok(userPage);
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest userRequest) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userRequest, userDO);
        userDO.setEnabled(true);
        userDO.setHasDeleted(false);
        String nickname = userRequest.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            nickname = userRequest.getUsername();
        }
        userDO.setNickname(nickname);
        userDO.setPassword(passwordEncoder.encode(UserConstants.DEFAULT_PASSWORD));
        List<Long> roleIds = userRequest.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
            Set<RoleDO> roles = roleService.findAllByIdIn(roleIds);
            userDO.setRoles(roles);
        }
        UserDO user = userService.saveUser(userDO);
        return ResponseEntity.ok(user.getId());
    }
}
