package com.joey.admin.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Joey
 * @create 2020-10-20 16:27
 * @desc UserPageRequest
 **/

@Getter
@Setter
@ToString
public class UserPageRequest extends BasePage{

  private String nickname;
}
