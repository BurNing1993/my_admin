package com.joey.admin.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joey.admin.system.dataobject.RoleDO;
import com.joey.admin.system.dataobject.UserDO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Joey
 * @create 2020-10-15 11:35
 * @desc JWT用户对象
 **/
public class JwtUser implements UserDetails {

    @Getter
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private Boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUser() {
    }

    private JwtUser(Long id, String username, String password, Boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }


    public static JwtUser create(UserDO userDO) {
        List<GrantedAuthority> authorities = userDO.getRoles().stream()
                .map(RoleDO::getRoleName)
                .map(roleNameEnum ->
                        new SimpleGrantedAuthority(roleNameEnum.getName())
                ).collect(Collectors.toList());

        return new JwtUser(
                userDO.getId(),
                userDO.getUsername(),
                userDO.getPassword(),
                userDO.getEnabled(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
