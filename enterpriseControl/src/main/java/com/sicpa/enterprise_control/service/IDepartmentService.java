package com.sicpa.enterprise_control.service;

import com.sicpa.enterprise_control.dto.DepartmentDTO;
import com.sicpa.enterprise_control.exception.ValidationException;

import java.util.Collection;

public interface IDepartmentService {
    DepartmentDTO create(DepartmentDTO departmentDTO) throws ValidationException;
    DepartmentDTO update(DepartmentDTO departmentDTO) throws ValidationException;
    Collection<DepartmentDTO> findAll();
    DepartmentDTO findById(String id);
}
