package com.sicpa.enterprise_control.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicpa.enterprise_control.util.Constants;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO extends AuditDTO{
    @JsonProperty(Constants.ID_LABEL)
    private String idDepartment;
    private String description;
    private String name;
    private String phone;
    private String idEnterprise;

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(String idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
