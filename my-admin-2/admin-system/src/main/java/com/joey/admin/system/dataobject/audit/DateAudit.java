package com.joey.admin.system.dataobject.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * @author Joey
 * @create 2020-10-12 20:33
 * @desc DateAudit
 **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class DateAudit {

    @JsonIgnore
    @CreatedDate
    private Instant createTime;

    @JsonIgnore
    @LastModifiedDate
    private Instant updateTime;
}
