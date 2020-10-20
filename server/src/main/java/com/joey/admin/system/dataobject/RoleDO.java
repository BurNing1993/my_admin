package com.joey.admin.system.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joey.admin.common.audit.UserDateAudit;
import com.joey.admin.system.enums.RoleNameEnum;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Joey
 * @create 2020-10-14 10:50
 * @desc RoleDO
 **/
@Data
@Entity
@Table(name = "role")
public class RoleDO extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) unsigned")
    private Long id;

    /**
     * 自然键（natural key）是单个或组合属性，他们必须唯一且非空。
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private RoleNameEnum roleName;

    @Column(columnDefinition = "bool default false")
    private Boolean hasDeleted;
}
