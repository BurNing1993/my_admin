package com.joey.admin.system.service.impl;

import com.joey.admin.common.exception.ResourceNotFoundException;
import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.enums.RoleNameEnum;
import com.joey.admin.system.repository.RoleRepository;
import com.joey.admin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Joey
 * @create 2020-10-17 18:23
 * @desc RoleServiceImpl
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDO findByRoleName(RoleNameEnum roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResourceNotFoundException("Role", "roleName", roleName));
    }

    @Override
    public List<RoleDO> findAllByIdIn(List<Long> roleIds) {
        return roleRepository.findAllByIdIn(roleIds);
    }

    @Override
    public List<RoleDO> getRoleList() {
        return  roleRepository.findAll();
    }
}
