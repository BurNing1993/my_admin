package com.joey.admin.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Joey
 * @create 2020-10-17 15:08
 * @desc LoginRequest
 **/

@Getter
@ToString
public class LoginRequest {

    @NotBlank(message = "账号不能为空")
    @Size(min = 6, max = 16, message = "账号为6~16位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码为6~16位")
    private String password;
}
