package com.sicpa.enterprise_control.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "ENTERPRISES")
public class Enterprise extends Audit{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_ENTERPRISE")
    private String idEnterprise;

    @Basic
    @Column(name = "NAME", length = 128)
    private String name;

    @Basic
    @Column(name = "ADDRESS", length = 256)
    private String address;

    @Basic
    @Column(name = "PHONE", length = 10)
    private String phone;

    @OneToMany(mappedBy = "enterprise")
    private Collection<Department> departments;

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

    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
