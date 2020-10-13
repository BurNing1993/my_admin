package com.joey.admin.system.security;

import com.joey.admin.system.dataobject.UserDO;
import com.joey.admin.system.exception.ResourceNotFoundException;
import com.joey.admin.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Joey
 * @create 2020-10-13 15:17
 * @desc CustomUserDetailsService
 **/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = UsernameNotFoundException.class)
    public UserDetails loadUserByUsername(String usernameOrCellphone) throws UsernameNotFoundException {
        UserDO userDO = userRepository.findByUsernameOrCellphone(usernameOrCellphone, usernameOrCellphone)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username or cellphone : " + usernameOrCellphone));
        return UserPrincipal.create(userDO);
    }

    /**
     * For JWTAuthenticationFilter
     * @param id
     * @return
     */
    @Transactional(rollbackFor = ResourceNotFoundException.class)
    public UserDetails loadUserById(Long id) {
        UserDO userDO = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(userDO);
    }


}
