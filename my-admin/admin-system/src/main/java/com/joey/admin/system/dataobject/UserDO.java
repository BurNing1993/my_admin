package com.joey.admin.system.dataobject;

import com.joey.admin.system.dataobject.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Joey
 * @create 2020-10-10 16:34
 * @desc UserDo
 **/
@Data
@Entity
@Table(name = "t_user")
public class UserDO extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigint(20) unsigned")
    private Long userId;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 64)
    private String nickname;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cellphone;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleDO> roles;

    @Column(length = 1, nullable = false, columnDefinition = "tinyint(1) default 0")
    private Integer hasDeleted;
}
