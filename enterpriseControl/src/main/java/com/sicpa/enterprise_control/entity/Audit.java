package com.sicpa.enterprise_control.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Audit {
    @Basic
    @Column(name = "CREATED_BY")
    private String createdBy;

    @Basic
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Basic
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Basic
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Basic
    @Column(name = "STATUS")
    private boolean status;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
