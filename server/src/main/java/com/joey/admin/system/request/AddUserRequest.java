package com.joey.admin.system.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Joey
 * @create 2020-10-30 13:46
 * @desc AddUserRequest
 **/

@Getter
public class AddUserRequest {
  @NotBlank(message = "用户名不能为空!")
  @Size(min = 6, max = 16, message = "用户名为6~16位!")
  private String username;
  private String nickname;
  @Email(message = "邮箱格式错误!")
  private String email;
  @Pattern(regexp = "/^(?:(?:\\+|00)86)?1[3-9]\\d{9}$/",message = "手机号码格式不正确!")
  private String cellphone;
  private List<Long> roleIds;
}
