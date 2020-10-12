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
import java.util.Optional;

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
                Predicate p1 = criteriaBuilder.like(root.get("nickname"),"%"+userDO.getNickname()+"%");
                return p1;
            }
        };
        return userRepository.findAll(userDOSpecification,pageable);
    }

    @Override
    public UserDO saveUser(UserDO userDO) {
        return userRepository.save(userDO);
    }

    @Override
    public UserDO getUser(Long userId) {
        Optional<UserDO> optionalUserDO= userRepository.findById(userId);
        return optionalUserDO.isPresent() ? optionalUserDO.get() : null;
    }
}
