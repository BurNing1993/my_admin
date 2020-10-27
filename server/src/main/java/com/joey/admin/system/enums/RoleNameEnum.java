package com.joey.admin.system.enums;

import lombok.Getter;

/**
 * @author Joey
 * @create 2020-10-14 10:53
 * @desc RoleNameEnum
 **/
@Getter
public enum RoleNameEnum {
    /**
     * 普通用户
     */
    USER("USER", "普通用户"),
    /**
     * 管理员
     */
    ADMIN("ADMIN", "管理员"),
    ;

    private String name;
    private String desc;

    RoleNameEnum(){}

    RoleNameEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
