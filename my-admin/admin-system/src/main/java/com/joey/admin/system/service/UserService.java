package com.joey.admin.system.service;

import com.joey.admin.system.dataobject.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Joey
 * @create 2020-10-10 17:01
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
}
