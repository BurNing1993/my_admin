package com.joey.admin.system.service;

import com.joey.admin.system.dataobject.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Joey
 * @create 2020-10-15 10:23
 * @desc UserService
 **/
public interface UserService {
    /**
     * getUserPage
     * @param userDO
     * @param pageable
     * @return
     */
    Page<UserDO> getUserPage(UserDO userDO, Pageable pageable);

    /**
     * saveUser
     * @param userDO
     * @return
     */
    UserDO saveUser(UserDO userDO);

    /**
     * getUserById
     * @param userId
     * @return
     */
    UserDO getUserById(Long userId);

    /**
     * 根据 username 查找用户
     * @param username
     * @return
     */
    UserDO getUserByUsername(String username);
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
