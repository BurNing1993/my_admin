package com.joey.admin.system.service;

import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.enums.RoleNameEnum;

import java.util.List;
import java.util.Set;

/**
 * @author Joey
 * @create 2020-10-17 18:21
 * @desc RoleService
 **/
public interface RoleService {
    /**
     * 根据 roleName 查找角色
     * @param roleName
     * @return
     */
     RoleDO findByRoleName(RoleNameEnum roleName);

    /**
     * 根据 roleIds 查找角色列表
     * @param roleIds
     * @return
     */
    Set<RoleDO> findAllByIdIn(List<Long> roleIds);

    /**
     *  角色列表
     * @return
     */
    List<RoleDO> getRoleList();
}
