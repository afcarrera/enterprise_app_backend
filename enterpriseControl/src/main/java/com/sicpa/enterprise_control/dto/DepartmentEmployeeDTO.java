package com.sicpa.enterprise_control.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicpa.enterprise_control.util.Constants;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentEmployeeDTO extends AuditDTO{
    @JsonProperty(Constants.ID_LABEL)
    private String idDepartmentEmployee;
    private String idDepartment;
    private String idEmployee;

    public String getIdDepartmentEmployee() {
        return idDepartmentEmployee;
    }

    public void setIdDepartmentEmployee(String idDepartmentEmployee) {
        this.idDepartmentEmployee = idDepartmentEmployee;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }
}
