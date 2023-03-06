package com.sicpa.enterprise_control.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "DEPARTMENTS_EMPLOYEES")
public class DepartmentEmployee extends Audit{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_DEP_EMP")
    private String idDepEmp;

    @Basic
    @Column(name = "ID_DEPARTMENT")
    private String idDepartment;

    @Basic
    @Column(name = "ID_EMPLOYEE")
    private String idEmployee;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTMENT", referencedColumnName = "ID_DEPARTMENT", nullable = false,
            insertable = false, updatable=false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLOYEE", referencedColumnName = "ID_EMPLOYEE", nullable = false,
            insertable = false, updatable=false)
    private Employee employee;

    public String getIdDepEmp() {
        return idDepEmp;
    }

    public void setIdDepEmp(String idDepEmp) {
        this.idDepEmp = idDepEmp;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
