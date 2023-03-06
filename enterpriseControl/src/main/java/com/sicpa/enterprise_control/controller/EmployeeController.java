package com.sicpa.enterprise_control.controller;

import com.sicpa.enterprise_control.dto.EmployeeDTO;
import com.sicpa.enterprise_control.dto.ResponseDTO;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.service.impl.EmployeeServiceImpl;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Messages;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping
    public ResponseDTO<Object> create(@RequestBody EmployeeDTO employeeDto)
            throws RequiredException, ValidationException{
        String requiredMessage = validateRequiredEmployee(employeeDto);
        if (Objects.nonNull(requiredMessage)){
            throw new RequiredException(requiredMessage);
        }
        employeeDto.setName(this.validateLetters(employeeDto.getName(),
                Messages.Errors.INVALID_NAME.toString()));
        employeeDto.setSurname(this.validateLetters(employeeDto.getSurname(),
                Messages.Errors.INVALID_SURNAME.toString()));
		employeeDto.setPosition(this.validateLetters(employeeDto.getPosition(),
				Messages.Errors.INVALID_POSITION.toString()));
        if (employeeDto.getAge() <= 0){
            throw new ValidationException(Messages.Errors.INVALID_AGE.toString());
        }
        String validMail = Util.validateEmail(employeeDto.getEmail());
        if (Objects.isNull(validMail)) {
            throw new ValidationException(Messages.Errors.INVALID_EMAIL.toString());
        }
        employeeDto.setCreatedDate(Util.getCurrentDate());
        employeeDto.setCreatedBy(Constants.USER_DEFAULT);
        return MappingDTO.getResponse(employeeServiceImpl.create(employeeDto));
    }

    @GetMapping
    public ResponseDTO<Object> findAll(){
        return MappingDTO.getResponse(employeeServiceImpl.findAll());
    }

    @GetMapping("/{idEmployee}")
    public ResponseDTO<Object> findById(@PathVariable("idEmployee") String id){
        return MappingDTO.getResponse(employeeServiceImpl.findById(id));
    }

    @PatchMapping("/{idEmployee}")
    public ResponseDTO<Object> update(@PathVariable("idEmployee") String id, @RequestBody EmployeeDTO employeeDto)
            throws ResourceNotFoundException, ValidationException {
        EmployeeDTO previousEmployee = this.employeeServiceImpl.findById(id);
        if (Objects.isNull(previousEmployee)){
            throw new ResourceNotFoundException(Messages.NotFound.NOT_FOUND_EMPLOYEE.toString());
        }else{
			this.validateOptionalEmployee(employeeDto, previousEmployee);
            if(Objects.nonNull(employeeDto.getEmail())){
                String validMail = Util.validateEmail(employeeDto.getEmail());
                if (Objects.isNull(validMail)) {
                    throw new ValidationException(Messages.Errors.INVALID_EMAIL.toString());
                }
                previousEmployee.setEmail(employeeDto.getEmail());
            }
            if(employeeDto.getAge() < 0) {
                throw new ValidationException(Messages.Errors.INVALID_AGE.toString());
            }else if (employeeDto.getAge() > 0){
                previousEmployee.setAge(employeeDto.getAge());
            }
            if(Objects.nonNull(employeeDto.getPosition())){
                previousEmployee.setPosition(this.validateLetters(employeeDto.getPosition(),
                        Messages.Errors.INVALID_POSITION.toString()));
            }
            previousEmployee.setModifiedBy(Constants.USER_DEFAULT);
            previousEmployee.setModifiedDate(Util.getCurrentDate());
        }
        return MappingDTO.getResponse(employeeServiceImpl.update(previousEmployee));
    }

    private String validateRequiredEmployee(EmployeeDTO employeeDto){
        if (Objects.isNull(employeeDto.getName())){
            return Messages.Required.EMPLOYEE_NAME.toString();
        }
        if (Objects.isNull(employeeDto.getSurname())){
            return Messages.Required.EMPLOYEE_SURNAME.toString();
        }
        if (Objects.isNull(employeeDto.getEmail())){
            return Messages.Required.EMPLOYEE_EMAIL.toString();
        }
        if (Objects.isNull(employeeDto.getAge())){
            return Messages.Required.EMPLOYEE_AGE.toString();
        }
        if (Objects.isNull(employeeDto.getPosition())){
            return Messages.Required.EMPLOYEE_POSITION.toString();
        }
        return null;
    }

	private void validateOptionalEmployee(EmployeeDTO employeeDto, EmployeeDTO previousEmployee)
		throws ValidationException{		
			if(Objects.nonNull(employeeDto.getName())){
				previousEmployee.setName(this.validateLetters(employeeDto.getName(),
						Messages.Errors.INVALID_NAME.toString()));
			}
			if(Objects.nonNull(employeeDto.getSurname())){
				previousEmployee.setSurname(this.validateLetters(employeeDto.getSurname(),
						Messages.Errors.INVALID_SURNAME.toString()));
			}
			if (Objects.nonNull(employeeDto.getPosition())){
				employeeDto.setSurname(this.validateLetters(employeeDto.getPosition(),
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
