package com.sicpa.enterprise_control.service;

import com.sicpa.enterprise_control.dto.DepartmentDTO;

import java.util.Collection;

public interface IDepartmentService {
    DepartmentDTO create(DepartmentDTO departmentDTO);
    DepartmentDTO update(DepartmentDTO departmentDTO);
    Collection<DepartmentDTO> findAll();
    DepartmentDTO findById(String id);
}
