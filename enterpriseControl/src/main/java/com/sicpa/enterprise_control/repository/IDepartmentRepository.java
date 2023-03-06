package com.sicpa.enterprise_control.repository;

import com.sicpa.enterprise_control.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, String> {
}
