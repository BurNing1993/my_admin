package com.joey.admin.system.repository;

import com.joey.admin.system.dataobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Joey
 * @create 2020-10-10 16:50
 * @desc UserDORepository
 **/
public interface UserRepository extends JpaRepository<UserDO,Long>, JpaSpecificationExecutor<UserDO> {
}
