package com.sicpa.enterprise_control.controller;

import com.sicpa.enterprise_control.dto.*;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.service.IEnterpriseService;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/enterprises")
public class EnterpriseController {
    @Autowired
    private IEnterpriseService iEnterpriseService;

    @PostMapping
    public ResponseDTO<Object> create(@RequestBody EnterpriseDTO enterpriseDto)
            throws RequiredException, ValidationException {
        enterpriseDto.setCreatedDate(Util.getCurrentDate());
        enterpriseDto.setCreatedBy(Constants.USER_DEFAULT);
        return MappingDTO.getResponse(this.iEnterpriseService.create(enterpriseDto));
    }

    @GetMapping
    public ResponseDTO<Object> findAll(){
        return MappingDTO.getResponse(this.iEnterpriseService.findAll());
    }

    @GetMapping("/{idEnterprise}")
    public ResponseDTO<Object> findById(@PathVariable("idEnterprise") String id){
        return MappingDTO.getResponse(this.iEnterpriseService.findById(id));
    }

    @PatchMapping("/{idEnterprise}")
    public ResponseDTO<Object> update(@PathVariable("idEnterprise") String id, @RequestBody EnterpriseDTO enterpriseDto)
            throws ResourceNotFoundException, ValidationException, ExecutionException, InterruptedException {
        enterpriseDto.setIdEnterprise(id);
        return MappingDTO.getResponse(this.iEnterpriseService.update(enterpriseDto));
    }
}
