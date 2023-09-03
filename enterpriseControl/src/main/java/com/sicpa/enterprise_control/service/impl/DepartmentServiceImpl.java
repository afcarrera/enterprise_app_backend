package com.sicpa.enterprise_control.service.impl;

import com.sicpa.enterprise_control.dto.DepartmentDTO;
import com.sicpa.enterprise_control.entity.Department;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.repository.IDepartmentRepository;
import com.sicpa.enterprise_control.service.IDepartmentService;
import com.sicpa.enterprise_control.dto.util.MappingDTO;

import com.sicpa.enterprise_control.service.IEnterpriseService;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Messages;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentRepository iDepartmentRepository;
    @Autowired
    private IEnterpriseService iEnterpriseService;

    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) throws ValidationException {
        String requiredMessage = validateRequiredDepartment(departmentDTO);
        if (Objects.nonNull(requiredMessage)){
            throw new RequiredException(requiredMessage);
        }
        if (Objects.isNull(iEnterpriseService.findById(departmentDTO.getIdEnterprise()))){
            throw new ValidationException(Messages.Errors.INVALID_ENTERPRISE.toString());
        }
        departmentDTO.setPhone(this.validatePhoneNumber(departmentDTO.getPhone(),
                Messages.Errors.INVALID_PHONE.toString()));
        Department department = new Department();
        department = (Department) MappingDTO.convertToEntity(departmentDTO, department);
        DepartmentDTO newDepartmentDTO = new DepartmentDTO();
        newDepartmentDTO = (DepartmentDTO) MappingDTO.convertToDto(
                iDepartmentRepository.save(department), newDepartmentDTO);
        return newDepartmentDTO;
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) throws ValidationException {
        DepartmentDTO previousDepartment = this.findById(departmentDTO.getIdDepartment());
        if (Objects.isNull(previousDepartment)){
            throw new ResourceNotFoundException(Messages.NotFound.NOT_FOUND_DEPARTMENT.toString());
        }else{
            if(Objects.nonNull(departmentDTO.getName())){
                previousDepartment.setName(departmentDTO.getName());
            }
            if(Objects.nonNull(departmentDTO.getDescription())){
                previousDepartment.setDescription(departmentDTO.getDescription());
            }
            if(Objects.nonNull(departmentDTO.getPhone())){
                previousDepartment.setPhone(this.validatePhoneNumber(departmentDTO.getPhone(),
                        Messages.Errors.INVALID_PHONE.toString()));
            }
            if(Objects.nonNull(departmentDTO.getIdEnterprise())){
                if (Objects.isNull(iEnterpriseService.findById(departmentDTO.getIdEnterprise()))){
                    throw new ValidationException(Messages.Errors.INVALID_ENTERPRISE.toString());
                }
                previousDepartment.setIdDepartment(departmentDTO.getIdDepartment());
            }
            previousDepartment.setModifiedBy(Constants.USER_DEFAULT);
            previousDepartment.setModifiedDate(Util.getCurrentDate());
        }
        Department department = new Department();
        department = (Department) MappingDTO.convertToEntity(previousDepartment, department);
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
