package com.joey.admin.system.repository;

import com.joey.admin.system.dataobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author Joey
 * @create 2020-10-14 17:07
 * @desc UserRepository
 **/
public interface UserRepository extends JpaRepository<UserDO, Long>, JpaSpecificationExecutor<UserDO> {
    /**
     * 根据 username 查找用户
     *
     * @param username
     * @return
     */
    Optional<UserDO> findByUsername(String username);

    /**
     * username 是否存在
     *
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);

    /**
     * cellphone 是否存在
     *
     * @param cellphone
     * @return
     */
    Boolean existsByCellphone(String cellphone);
}
