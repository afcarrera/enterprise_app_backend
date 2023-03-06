package com.sicpa.enterprise_control.repository;

import com.sicpa.enterprise_control.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, String> {
}
