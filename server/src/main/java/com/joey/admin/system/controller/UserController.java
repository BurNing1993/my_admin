package com.joey.admin.system.controller;

import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.request.UserPageRequest;
import com.joey.admin.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/user/page")
  public ResponseEntity<?> getUserPage(UserPageRequest userPageRequest){
    UserDO userDO =new UserDO();
    BeanUtils.copyProperties(userPageRequest,userDO);
    Pageable page = PageRequest.of(userPageRequest.getPage()-1,userPageRequest.getSize(), Sort.Direction.DESC,"id");
    Page<UserDO> userPage = userService.getUserPage(userDO, page);
    log.info("UserPageRequest:",userPageRequest.toString());
    return  ResponseEntity.ok(userPage);
  }
}
