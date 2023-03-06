package com.sicpa.enterprise_control.repository;

import com.sicpa.enterprise_control.entity.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, String> {
}
