package com.joey.admin.system.payload;

import lombok.Data;

/**
 * @author Joey
 * @create 2020-10-13 16:41
 * @desc JwtAuthenticationResponse
 **/
@Data
public class JwtAuthenticationResponse {

    private String accessToken;

    private String tokenType = "Bearer";
}
