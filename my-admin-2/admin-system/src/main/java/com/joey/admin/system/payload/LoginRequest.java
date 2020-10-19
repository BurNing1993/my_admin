package com.joey.admin.system.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Joey
 * @create 2020-10-13 16:32
 * @desc LoginRequest
 **/
@Data
public class LoginRequest {

    @NotBlank(message = "账号不能为空")
    @Size(min = 6, max = 16, message = "账号为6~16位")
    private String usernameOrCellPhone;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码为6~16位")
    private String password;

}
