package com.joey.admin.system.service.impl;

import com.joey.admin.common.exception.ResourceNotFoundException;
import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.repository.UserRepository;
import com.joey.admin.system.service.UserService;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(userDO.getNickname())) {
          Path<String> nickname = root.get("nickname");
          Predicate p1 = criteriaBuilder.like(nickname, "%" + userDO.getNickname() + "%");
          predicates.add(p1);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
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
