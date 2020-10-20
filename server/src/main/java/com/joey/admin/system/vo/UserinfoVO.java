package com.joey.admin.system.vo;

import lombok.Data;

import java.util.Set;

/**
 * @author Joey
 * @create 2020-10-20 11:36
 * @desc UserInfoVO
 **/
@Data
public class UserinfoVO {
  private String username;
  private String nickname;
  private String email;
  private String cellphone;
  private Set<String> roles;
  private Boolean enabled;
}
