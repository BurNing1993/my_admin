package com.joey.admin.system.service.impl;

import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.repository.UserRepository;
import com.joey.admin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Joey
 * @create 2020-10-10 18:14
 * @desc UserServiceImpl
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserDO> getUserPage(UserDO userDO, Pageable pageable) {
        Specification<UserDO> userDOSpecification =new Specification<UserDO>() {
            @Override
            public Predicate toPredicate(Root<UserDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        return userRepository.findAll(userDOSpecification,pageable);
    }
}
