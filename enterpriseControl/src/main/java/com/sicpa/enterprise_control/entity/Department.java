package com.sicpa.enterprise_control.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "DEPARTMENTS")
public class Department extends Audit{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_DEPARTMENT")
    private String idDepartment;

    @Basic
    @Column(name = "ID_ENTERPRISE")
    private String idEnterprise;

    @ManyToOne
    @JoinColumn(name = "ID_ENTERPRISE", referencedColumnName = "ID_ENTERPRISE", nullable = false,
            insertable = false, updatable=false)
    private Enterprise enterprise;

    @Basic
    @Column(name = "DESCRIPTION", length = 256)
    private String description;

    @Basic
    @Column(name = "NAME", length = 128)
    private String name;

    @Basic
    @Column(name = "PHONE", length = 10)
    private String phone;

    @OneToMany(mappedBy = "department")
    private Collection<DepartmentEmployee> employees;

    public Collection<DepartmentEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<DepartmentEmployee> employees) {
        this.employees = employees;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(String idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
