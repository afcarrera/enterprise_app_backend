package com.sicpa.enterprise_control.service.impl;

import com.sicpa.enterprise_control.dto.EmployeeDTO;
import com.sicpa.enterprise_control.entity.Employee;
import com.sicpa.enterprise_control.repository.IEmployeeRepository;
import com.sicpa.enterprise_control.service.IEmployeeService;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee = (Employee) MappingDTO.convertToEntity(employeeDTO, employee);
        EmployeeDTO newEmployeeDTO = new EmployeeDTO();
        newEmployeeDTO = (EmployeeDTO) MappingDTO.convertToDto(
                iEmployeeRepository.save(employee), newEmployeeDTO);
        return newEmployeeDTO;
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee = (Employee) MappingDTO.convertToEntity(employeeDTO, employee);
        EmployeeDTO updatedEmployeeDTO = new EmployeeDTO();
        updatedEmployeeDTO = (EmployeeDTO) MappingDTO.convertToDto(
                iEmployeeRepository.save(employee), updatedEmployeeDTO);
        return updatedEmployeeDTO;
    }

    @Override
    public Collection<EmployeeDTO> findAll() {
        List<Employee> allEmployees = iEmployeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS  = new ArrayList<>();
        EmployeeDTO employeeDTO;
        for (Employee employee : allEmployees){
            employeeDTO = new EmployeeDTO();
            employeeDTO = (EmployeeDTO) MappingDTO.convertToDto(
                    employee, employeeDTO);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO findById(String id) {
        Employee employeeById = findEmployeeById(id);
        if (employeeById == null){
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO = (EmployeeDTO) MappingDTO.convertToDto(
                employeeById, employeeDTO);
        return employeeDTO;
    }

    private Employee findEmployeeById(String id){
        Optional<Employee> employeeOptional = iEmployeeRepository.findById(id);
        return employeeOptional.orElse(null);
    }
}
