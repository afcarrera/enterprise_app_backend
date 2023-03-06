package com.sicpa.enterprise_control.service;

import com.sicpa.enterprise_control.dto.EmployeeDTO;

import java.util.Collection;

public interface IEmployeeService {
    EmployeeDTO create(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO);
    Collection<EmployeeDTO> findAll();
    EmployeeDTO findById(String id);
}
