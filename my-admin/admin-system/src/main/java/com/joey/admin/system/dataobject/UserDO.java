package com.joey.admin.system.dataobject;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Joey
 * @create 2020-10-10 16:34
 * @desc UserDo
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user")
public class UserDO {

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

    @Column(nullable = false, unique = true, length = 11)
    private String cellphone;

    @Column(length = 1, nullable = false, columnDefinition = "tinyint(1) default 0")
    private Integer hasDeleted;

    @CreatedDate
    private Timestamp createTime;

    private Long createBy;

    @LastModifiedDate
    private Timestamp updateTime;

    private Long updateBy;

}
