package com.sicpa.enterprise_control.service;

import com.sicpa.enterprise_control.dto.DepartmentEmployeeDTO;

import java.util.Collection;

public interface IDepartmentEmployeeService {
    DepartmentEmployeeDTO create(DepartmentEmployeeDTO departmentEmployeeDTO);
    DepartmentEmployeeDTO update(DepartmentEmployeeDTO departmentEmployeeDTO);
    Collection<DepartmentEmployeeDTO> findAll();
    DepartmentEmployeeDTO findById(String id);
}
