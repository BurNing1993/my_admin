package com.joey.admin.common.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

/**
 * @author Joey
 * @create 2020-10-14 10:22
 * @desc UserDateAudit
 **/
@MappedSuperclass
@Setter
@Getter
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

}
