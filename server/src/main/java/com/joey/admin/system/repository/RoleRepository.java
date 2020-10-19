package com.joey.admin.system.repository;

import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Joey
 * @create 2020-10-15 10:14
 * @desc Role
 **/
public interface RoleRepository extends JpaRepository<RoleDO,Long> {

    /**
     * 根据 roleName 查找角色
     * @param roleName
     * @return
     */
    Optional<RoleDO> findByRoleName(RoleNameEnum roleName);

    /**
     * 根据 roleIds 查找角色列表
     * @param roleIds
     * @return
     */
    List<RoleDO> findAllByIdIn(List<Long> roleIds);
}
