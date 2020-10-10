package com.joey.admin.system.dataobject;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
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
public class UserDO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "UNSIGNED INT(11)")
    private Long userId;

    @CreatedDate
    private Timestamp createTime;

    private Long createBy;

    @LastModifiedDate
    private Timestamp updateTime;

    private Long updateBy;

}
