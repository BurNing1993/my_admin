package com.joey.admin.system.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Joey
 * @create 2020-10-13 16:37
 * @desc SignUpRequest
 **/
@Data
public class SignUpRequest {

    @Size(min = 6, max = 40)
    private String name;

    @NotBlank
    @Size(min = 6, max = 16)
    private String username;

    @NotBlank
    @Size(min = 6, max = 16)
    private String password;
}
