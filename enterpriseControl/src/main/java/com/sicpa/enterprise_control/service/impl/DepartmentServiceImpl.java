package com.sicpa.enterprise_control.service.impl;

import com.sicpa.enterprise_control.dto.DepartmentDTO;
import com.sicpa.enterprise_control.entity.Department;
import com.sicpa.enterprise_control.repository.IDepartmentRepository;
import com.sicpa.enterprise_control.service.IDepartmentService;
import com.sicpa.enterprise_control.dto.util.MappingDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentRepository iDepartmentRepository;

    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department = (Department) MappingDTO.convertToEntity(departmentDTO, department);
        DepartmentDTO newDepartmentDTO = new DepartmentDTO();
        newDepartmentDTO = (DepartmentDTO) MappingDTO.convertToDto(
                iDepartmentRepository.save(department), newDepartmentDTO);
        return newDepartmentDTO;
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department = (Department) MappingDTO.convertToEntity(departmentDTO, department);
        DepartmentDTO updatedDepartmentDTO = new DepartmentDTO();
        updatedDepartmentDTO = (DepartmentDTO) MappingDTO.convertToDto(
                iDepartmentRepository.save(department), updatedDepartmentDTO);
        return updatedDepartmentDTO;
    }

    @Override
    public Collection<DepartmentDTO> findAll() {
        List<Department> allDepartments = iDepartmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS  = new ArrayList<>();
        DepartmentDTO departmentDTO;
        for (Department department : allDepartments){
            departmentDTO = new DepartmentDTO();
            departmentDTO = (DepartmentDTO) MappingDTO.convertToDto(
                    department, departmentDTO);
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }

    @Override
    public DepartmentDTO findById(String id) {
        Department departmentById = findDepartmentById(id);
        if (departmentById == null){
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO = (DepartmentDTO) MappingDTO.convertToDto(
                departmentById, departmentDTO);
        return departmentDTO;
    }

    private Department findDepartmentById(String id){
        Optional<Department> departmentOptional = iDepartmentRepository.findById(id);
        return departmentOptional.orElse(null);
    }
}
