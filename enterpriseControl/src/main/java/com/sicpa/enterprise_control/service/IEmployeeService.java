package com.sicpa.enterprise_control.service;

import com.sicpa.enterprise_control.dto.EmployeeDTO;
import com.sicpa.enterprise_control.exception.ValidationException;

import java.util.Collection;

public interface IEmployeeService {
    EmployeeDTO create(EmployeeDTO employeeDTO) throws ValidationException;
    EmployeeDTO update(EmployeeDTO employeeDTO) throws ValidationException;
    Collection<EmployeeDTO> findAll();
    EmployeeDTO findById(String id);
}
