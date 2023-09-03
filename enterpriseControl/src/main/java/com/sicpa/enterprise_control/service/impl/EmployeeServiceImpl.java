package com.sicpa.enterprise_control.service.impl;

import com.sicpa.enterprise_control.dto.EmployeeDTO;
import com.sicpa.enterprise_control.entity.Employee;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.repository.IEmployeeRepository;
import com.sicpa.enterprise_control.service.IEmployeeService;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Messages;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) throws ValidationException {
        String requiredMessage = validateRequiredEmployee(employeeDTO);
        if (Objects.nonNull(requiredMessage)){
            throw new RequiredException(requiredMessage);
        }
        employeeDTO.setName(this.validateLetters(employeeDTO.getName(),
                Messages.Errors.INVALID_NAME.toString()));
        employeeDTO.setSurname(this.validateLetters(employeeDTO.getSurname(),
                Messages.Errors.INVALID_SURNAME.toString()));
        employeeDTO.setPosition(this.validateLetters(employeeDTO.getPosition(),
                Messages.Errors.INVALID_POSITION.toString()));
        if (employeeDTO.getAge() <= 0){
            throw new ValidationException(Messages.Errors.INVALID_AGE.toString());
        }
        String validMail = Util.validateEmail(employeeDTO.getEmail());
        if (Objects.isNull(validMail)) {
            throw new ValidationException(Messages.Errors.INVALID_EMAIL.toString());
        }
        Employee employee = new Employee();
        employee = (Employee) MappingDTO.convertToEntity(employeeDTO, employee);
        EmployeeDTO newEmployeeDTO = new EmployeeDTO();
        newEmployeeDTO = (EmployeeDTO) MappingDTO.convertToDto(
                iEmployeeRepository.save(employee), newEmployeeDTO);
        return newEmployeeDTO;
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) throws ValidationException {
        EmployeeDTO previousEmployee = this.findById(employeeDTO.getIdEmployee());
        if (Objects.isNull(previousEmployee)){
            throw new ResourceNotFoundException(Messages.NotFound.NOT_FOUND_EMPLOYEE.toString());
        }else{
            this.validateOptionalEmployee(employeeDTO, previousEmployee);
            if(Objects.nonNull(employeeDTO.getEmail())){
                String validMail = Util.validateEmail(employeeDTO.getEmail());
                if (Objects.isNull(validMail)) {
                    throw new ValidationException(Messages.Errors.INVALID_EMAIL.toString());
                }
                previousEmployee.setEmail(employeeDTO.getEmail());
            }
            if(employeeDTO.getAge() < 0) {
                throw new ValidationException(Messages.Errors.INVALID_AGE.toString());
            }else if (employeeDTO.getAge() > 0){
                previousEmployee.setAge(employeeDTO.getAge());
            }
            if(Objects.nonNull(employeeDTO.getPosition())){
                previousEmployee.setPosition(this.validateLetters(employeeDTO.getPosition(),
                        Messages.Errors.INVALID_POSITION.toString()));
            }
            previousEmployee.setModifiedBy(Constants.USER_DEFAULT);
            previousEmployee.setModifiedDate(Util.getCurrentDate());
        }
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

    private String validateRequiredEmployee(EmployeeDTO employeeDTO){
        if (Objects.isNull(employeeDTO.getName())){
            return Messages.Required.EMPLOYEE_NAME.toString();
        }
        if (Objects.isNull(employeeDTO.getSurname())){
            return Messages.Required.EMPLOYEE_SURNAME.toString();
        }
        if (Objects.isNull(employeeDTO.getEmail())){
            return Messages.Required.EMPLOYEE_EMAIL.toString();
        }
        if (Objects.isNull(employeeDTO.getAge())){
            return Messages.Required.EMPLOYEE_AGE.toString();
        }
        if (Objects.isNull(employeeDTO.getPosition())){
            return Messages.Required.EMPLOYEE_POSITION.toString();
        }
        return null;
    }

    private void validateOptionalEmployee(EmployeeDTO employeeDTO, EmployeeDTO previousEmployee)
            throws ValidationException {
        if(Objects.nonNull(employeeDTO.getName())){
            previousEmployee.setName(this.validateLetters(employeeDTO.getName(),
                    Messages.Errors.INVALID_NAME.toString()));
        }
        if(Objects.nonNull(employeeDTO.getSurname())){
            previousEmployee.setSurname(this.validateLetters(employeeDTO.getSurname(),
                    Messages.Errors.INVALID_SURNAME.toString()));
        }
        if (Objects.nonNull(employeeDTO.getPosition())){
            employeeDTO.setSurname(this.validateLetters(employeeDTO.getPosition(),
                    Messages.Errors.INVALID_POSITION.toString()));
        }
    }

    private String validateLetters(String onlyLetters, String message) throws ValidationException {
        String validated = Util.validateLetters(onlyLetters);
        if (Objects.isNull(validated)) {
            throw new ValidationException(message);
        }
        return validated;
    }
}
