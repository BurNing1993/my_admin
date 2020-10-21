package com.joey.admin.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * @author Joey
 * @create 2020-10-20 16:17
 * @desc BasePage
 **/
@Getter
@Setter
public class BasePage {
  @Min(0)
  private Integer page = 0;
  @Min(1)
  private Integer size = 10;
}
