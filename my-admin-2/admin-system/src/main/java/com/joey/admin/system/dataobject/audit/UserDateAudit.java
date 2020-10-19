package com.joey.admin.system.dataobject.audit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

/**
 * @author Joey
 * @create 2020-10-13 10:04
 * @desc UserDateAudit
 **/

@MappedSuperclass
@Setter
@Getter
public abstract class UserDateAudit extends DateAudit{

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

}
