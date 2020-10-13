package com.joey.admin.system.repository;

import com.joey.admin.system.dataobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Joey
 * @create 2020-10-10 16:50
 * @desc UserDORepository
 **/
public interface UserRepository extends JpaRepository<UserDO,Long>, JpaSpecificationExecutor<UserDO> {

    /**
     * 根据 username 查找用户
     * @param username
     * @return
     */
    Optional<UserDO> findByUsernameOrCellphone(String username,String cellphone);
}
