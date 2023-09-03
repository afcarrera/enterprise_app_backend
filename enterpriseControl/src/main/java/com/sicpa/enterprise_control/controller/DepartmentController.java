package com.sicpa.enterprise_control.controller;

import com.sicpa.enterprise_control.dto.DepartmentDTO;
import com.sicpa.enterprise_control.dto.ResponseDTO;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.service.IDepartmentService;
import com.sicpa.enterprise_control.util.Constants;
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
        departmentDto.setCreatedDate(Util.getCurrentDate());
        departmentDto.setCreatedBy(Constants.USER_DEFAULT);
        return MappingDTO.getResponse(this.iDepartmentService.create(departmentDto));
    }

    @GetMapping
    public ResponseDTO<Object> findAll(){
        return MappingDTO.getResponse(this.iDepartmentService.findAll());
    }

    @GetMapping("/{idDepartment}")
    public ResponseDTO<Object> findById(@PathVariable("idDepartment") String id){
        return MappingDTO.getResponse(this.iDepartmentService.findById(id));
    }

    @PatchMapping("/{idDepartment}")
    public ResponseDTO<Object> update(@PathVariable("idDepartment") String id, @RequestBody DepartmentDTO departmentDto)
            throws ResourceNotFoundException, ValidationException {
        departmentDto.setIdDepartment(id);
        return MappingDTO.getResponse(this.iDepartmentService.update(departmentDto));
    }
}
