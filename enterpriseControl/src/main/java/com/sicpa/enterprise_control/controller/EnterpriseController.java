package com.sicpa.enterprise_control.controller;

import com.sicpa.enterprise_control.dto.*;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.service.impl.EnterpriseServiceImpl;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Messages;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/enterprises")
public class EnterpriseController {
    @Autowired
    private EnterpriseServiceImpl enterpriseServiceImpl;

    @PostMapping
    public ResponseDTO<Object> create(@RequestBody EnterpriseDTO enterpriseDto)
            throws RequiredException, ValidationException {
        String requiredMessage = validateRequiredEnterprise(enterpriseDto);
        if (Objects.nonNull(requiredMessage)){
            throw new RequiredException(requiredMessage);
        }
        enterpriseDto.setAddress(enterpriseDto.getAddress());
		enterpriseDto.setPhone(this.validatePhoneNumber(enterpriseDto.getPhone(),
				Messages.Errors.INVALID_PHONE.toString()));
        enterpriseDto.setCreatedDate(Util.getCurrentDate());
        enterpriseDto.setCreatedBy(Constants.USER_DEFAULT);
        return MappingDTO.getResponse(enterpriseServiceImpl.create(enterpriseDto));
    }

    @GetMapping
    public ResponseDTO<Object> findAll(){
        return MappingDTO.getResponse(enterpriseServiceImpl.findAll());
    }

    @GetMapping("/{idEnterprise}")
    public ResponseDTO<Object> findById(@PathVariable("idEnterprise") String id){
        return MappingDTO.getResponse(enterpriseServiceImpl.findById(id));
    }

    @PatchMapping("/{idEnterprise}")
    public ResponseDTO<Object> update(@PathVariable("idEnterprise") String id, @RequestBody EnterpriseDTO enterpriseDto)
            throws ResourceNotFoundException, ValidationException {
        EnterpriseDTO previousEnterprise = this.enterpriseServiceImpl.findById(id);
        if (Objects.isNull(previousEnterprise)){
            throw new ResourceNotFoundException(Messages.NotFound.NOT_FOUND_ENTERPRISE.toString());
        }else{
            if(Objects.nonNull(enterpriseDto.getName())){
                previousEnterprise.setName(enterpriseDto.getName());
            }
            if(Objects.nonNull(enterpriseDto.getAddress())){
                previousEnterprise.setAddress(enterpriseDto.getAddress());
            }
            if(Objects.nonNull(enterpriseDto.getPhone())){
                previousEnterprise.setPhone(this.validatePhoneNumber(enterpriseDto.getPhone(),
                        Messages.Errors.INVALID_PHONE.toString()));
            }
            previousEnterprise.setModifiedBy(Constants.USER_DEFAULT);
            previousEnterprise.setModifiedDate(Util.getCurrentDate());
        }
        return MappingDTO.getResponse(enterpriseServiceImpl.update(previousEnterprise));
    }

    private String validateRequiredEnterprise(EnterpriseDTO enterpriseDto){
        if (Objects.isNull(enterpriseDto.getName())){
            return Messages.Required.ENTERPRISE_NAME.toString();
        }
        if (Objects.isNull(enterpriseDto.getAddress())){
            return Messages.Required.ENTERPRISE_ADDRESS.toString();
        }
        if (Objects.isNull(enterpriseDto.getPhone())){
            return Messages.Required.ENTERPRISE_PHONE.toString();
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
