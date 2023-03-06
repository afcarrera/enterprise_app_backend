package com.sicpa.enterprise_control.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Collection;

@Entity(name = "EMPLOYEES")
public class Employee extends Audit{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_EMPLOYEE")
    private String idEmployee;

    @Basic
    @Column(name = "AGE")
    private short age;

    @Basic
    @Column(name = "EMAIL", length = 128)
    private String email;

    @Basic
    @Column(name = "NAME", length = 128)
    private String name;

    @Basic
    @Column(name = "POSITION", length = 64)
    private String position;

    @Basic
    @Column(name = "SURNAME", length = 128)
    private String surname;

    @OneToMany(mappedBy = "employee")
    private Collection<DepartmentEmployee> departments;

    public Collection<DepartmentEmployee> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<DepartmentEmployee> departments) {
        this.departments = departments;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String idPosition) {
        this.position = idPosition;
    }
}
