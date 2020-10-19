package com.joey.admin.system.dataobject;

import com.joey.admin.system.dataobject.audit.UserDateAudit;
import com.joey.admin.system.enums.RoleNameEnum;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * @author Joey
 * @create 2020-10-13 10:15
 * @desc RoleDO
 **/
@Data
@Entity
@Table(name = "t_role")
public class RoleDO extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigint(20) unsigned")
    private Long roleId;

    /**
     * 自然键（natural key）是单个或组合属性，他们必须唯一且非空。
     */
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 64)
    private RoleNameEnum roleName;

    @Column(length = 1, nullable = false, columnDefinition = "tinyint(1) default 0")
    private Integer hasDeleted;
}
