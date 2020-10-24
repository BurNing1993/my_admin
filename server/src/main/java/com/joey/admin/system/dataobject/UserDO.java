package com.joey.admin.system.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joey.admin.common.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Joey
 * @create 2020-10-14 10:26
 * @desc UserDO
 **/
@Data
@Entity
@Table(name = "user")
public class UserDO extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) unsigned")
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 64)
    private String nickname;

    @Column(length = 64)
    private String email;

    @Column(unique = true, length = 11)
    private String cellphone;

    @Column(columnDefinition = "bool default true")
    private Boolean enabled;

    @ManyToMany(targetEntity = RoleDO.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<RoleDO> roles;

    @Column(columnDefinition = "bool default false")
    private Boolean hasDeleted;
}
