package com.sicpa.enterprise_control.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicpa.enterprise_control.util.Constants;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnterpriseDTO extends AuditDTO{
    @JsonProperty(Constants.ID_LABEL)
    private String idEnterprise;
    private String name;
    private String address;
    private String phone;

    public String getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(String idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
