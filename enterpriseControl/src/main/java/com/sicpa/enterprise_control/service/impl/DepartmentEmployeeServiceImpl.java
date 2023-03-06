package com.sicpa.enterprise_control.service.impl;

import com.sicpa.enterprise_control.dto.DepartmentEmployeeDTO;
import com.sicpa.enterprise_control.entity.DepartmentEmployee;
import com.sicpa.enterprise_control.repository.IDepartmentEmployeeRepository;
import com.sicpa.enterprise_control.service.IDepartmentEmployeeService;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentEmployeeServiceImpl implements IDepartmentEmployeeService {
    @Autowired
    private IDepartmentEmployeeRepository iDepartmentEmployeeRepository;
    @Override
    public DepartmentEmployeeDTO create(DepartmentEmployeeDTO departmentEmployeeDTO) {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee = (DepartmentEmployee) MappingDTO.convertToEntity(departmentEmployeeDTO, departmentEmployee);
        DepartmentEmployeeDTO newDepartmentEmployeeDTO = new DepartmentEmployeeDTO();
        newDepartmentEmployeeDTO = (DepartmentEmployeeDTO) MappingDTO.convertToDto(
                iDepartmentEmployeeRepository.save(departmentEmployee), newDepartmentEmployeeDTO);
        return newDepartmentEmployeeDTO;
    }

    @Override
    public DepartmentEmployeeDTO update(DepartmentEmployeeDTO departmentEmployeeDTO) {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee = (DepartmentEmployee) MappingDTO.convertToEntity(departmentEmployeeDTO, departmentEmployee);
        DepartmentEmployeeDTO updatedDepartmentEmployeeDTO = new DepartmentEmployeeDTO();
        updatedDepartmentEmployeeDTO = (DepartmentEmployeeDTO) MappingDTO.convertToDto(
                iDepartmentEmployeeRepository.save(departmentEmployee), updatedDepartmentEmployeeDTO);
        return updatedDepartmentEmployeeDTO;
    }

    @Override
    public Collection<DepartmentEmployeeDTO> findAll() {
        List<DepartmentEmployee> allDepartmentEmployees = iDepartmentEmployeeRepository.findAll();
        List<DepartmentEmployeeDTO> departmentEmployeeDTOS  = new ArrayList<>();
        DepartmentEmployeeDTO departmentEmployeeDTO;
        for (DepartmentEmployee departmentEmployee : allDepartmentEmployees){
            departmentEmployeeDTO = new DepartmentEmployeeDTO();
            departmentEmployeeDTO = (DepartmentEmployeeDTO) MappingDTO.convertToDto(
                    departmentEmployee, departmentEmployeeDTO);
            departmentEmployeeDTOS.add(departmentEmployeeDTO);
        }
        return departmentEmployeeDTOS;
    }

    @Override
    public DepartmentEmployeeDTO findById(String id) {
        DepartmentEmployee departmentEmployeeById = findDepartmentEmployeeById(id);
        if (departmentEmployeeById == null){
            return null;
        }
        DepartmentEmployeeDTO departmentEmployeeDTO = new DepartmentEmployeeDTO();
        departmentEmployeeDTO = (DepartmentEmployeeDTO) MappingDTO.convertToDto(
                departmentEmployeeById, departmentEmployeeDTO);
        return departmentEmployeeDTO;
    }

    private DepartmentEmployee findDepartmentEmployeeById(String id){
        Optional<DepartmentEmployee> departmentEmployeeOptional = iDepartmentEmployeeRepository.findById(id);
        return departmentEmployeeOptional.orElse(null);
    }
}
