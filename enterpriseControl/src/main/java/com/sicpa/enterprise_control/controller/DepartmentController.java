package com.sicpa.enterprise_control.controller;

import com.sicpa.enterprise_control.dto.DepartmentDTO;
import com.sicpa.enterprise_control.dto.ResponseDTO;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.service.IDepartmentService;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Messages;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService iDepartmentService;

    @Autowired
    private EnterpriseController enterpriseController;

    @PostMapping
    public ResponseDTO<Object> create(@RequestBody DepartmentDTO departmentDto)
            throws RequiredException, ValidationException {
        String requiredMessage = validateRequiredDepartment(departmentDto);
        if (Objects.nonNull(requiredMessage)){
            throw new RequiredException(requiredMessage);
        }
        if (Objects.isNull(enterpriseController.findById(departmentDto.getIdEnterprise()).getData())){
            throw new ValidationException(Messages.Errors.INVALID_ENTERPRISE.toString());
        }
		departmentDto.setPhone(this.validatePhoneNumber(departmentDto.getPhone(),
				Messages.Errors.INVALID_PHONE.toString()));
        departmentDto.setCreatedDate(Util.getCurrentDate());
        departmentDto.setCreatedBy(Constants.USER_DEFAULT);
        return MappingDTO.getResponse(iDepartmentService.create(departmentDto));
    }

    @GetMapping
    public ResponseDTO<Object> findAll(){
        return MappingDTO.getResponse(iDepartmentService.findAll());
    }

    @GetMapping("/{idDepartment}")
    public ResponseDTO<Object> findById(@PathVariable("idDepartment") String id){
        return MappingDTO.getResponse(iDepartmentService.findById(id));
    }

    @PatchMapping("/{idDepartment}")
    public ResponseDTO<Object> update(@PathVariable("idDepartment") String id, @RequestBody DepartmentDTO departmentDto)
            throws ResourceNotFoundException, ValidationException {
        DepartmentDTO previousDepartment = this.iDepartmentService.findById(id);
        if (Objects.isNull(previousDepartment)){
            throw new ResourceNotFoundException(Messages.NotFound.NOT_FOUND_DEPARTMENT.toString());
        }else{
            if(Objects.nonNull(departmentDto.getName())){
                previousDepartment.setName(departmentDto.getName());
            }
            if(Objects.nonNull(departmentDto.getDescription())){
                previousDepartment.setDescription(departmentDto.getDescription());
            }
            if(Objects.nonNull(departmentDto.getPhone())){
                previousDepartment.setPhone(this.validatePhoneNumber(departmentDto.getPhone(),
                        Messages.Errors.INVALID_PHONE.toString()));
            }
            if(Objects.nonNull(departmentDto.getIdEnterprise())){
                if (Objects.isNull(enterpriseController.findById(departmentDto.getIdEnterprise()).getData())){
                    throw new ValidationException(Messages.Errors.INVALID_ENTERPRISE.toString());
                }
                previousDepartment.setIdDepartment(departmentDto.getIdDepartment());
            }
            previousDepartment.setModifiedBy(Constants.USER_DEFAULT);
            previousDepartment.setModifiedDate(Util.getCurrentDate());
        }
        return MappingDTO.getResponse(iDepartmentService.update(previousDepartment));
    }

    private String validateRequiredDepartment(DepartmentDTO departmentDto){
        if (Objects.isNull(departmentDto.getName())){
            return Messages.Required.DEPARTMENT_NAME.toString();
        }
        if (Objects.isNull(departmentDto.getDescription())){
            return Messages.Required.DEPARTMENT_DESCRIPTION.toString();
        }
        if (Objects.isNull(departmentDto.getIdEnterprise())){
            return Messages.Required.DEPARTMENT_ENTERPRISE.toString();
        }
        if (Objects.isNull(departmentDto.getPhone())){
            return Messages.Required.DEPARTMENT_PHONE.toString();
        }
        return null;
    }

    private String validatePhoneNumber(String onlyNumbers, String message) throws ValidationException {
        String validated = Util.validatePhoneNumber(onlyNumbers);
        if (Objects.isNull(validated)) {
            throw new ValidationException(message);
        }
        return validated;
    }
}
