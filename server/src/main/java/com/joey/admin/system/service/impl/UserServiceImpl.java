package com.joey.admin.system.service.impl;

import com.joey.admin.common.exception.ResourceNotFoundException;
import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.repository.UserRepository;
import com.joey.admin.system.service.UserService;
import lombok.RequiredArgsConstructor;
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
 * @create 2020-10-15 10:30
 * @desc UserServiceImpl
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserDO> getUserPage(UserDO userDO, Pageable pageable) {
        Specification<UserDO> userDOSpecification = userDO == null ? null : new Specification<UserDO>() {
            @Override
            public Predicate toPredicate(Root<UserDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.like(root.get("nickname"), "%" + userDO.getNickname() + "%");
                return p1;
            }
        };
        return userRepository.findAll(userDOSpecification, pageable);
    }

    @Override
    public UserDO saveUser(UserDO userDO) {
        return userRepository.save(userDO);
    }

    @Override
    public UserDO getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserDO", "userId", userId));
    }

    @Override
    public UserDO getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("UserDO", "username", username));
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);

    }

    @Override
    public Boolean existsByCellphone(String cellphone) {
        return userRepository.existsByCellphone(cellphone);
    }
}
