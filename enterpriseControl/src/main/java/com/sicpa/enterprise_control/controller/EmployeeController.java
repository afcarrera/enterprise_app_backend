package com.sicpa.enterprise_control.controller;

import com.sicpa.enterprise_control.dto.EmployeeDTO;
import com.sicpa.enterprise_control.dto.ResponseDTO;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.service.IEmployeeService;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping
    public ResponseDTO<Object> create(@RequestBody EmployeeDTO employeeDto)
            throws RequiredException, ValidationException{
        employeeDto.setCreatedDate(Util.getCurrentDate());
        employeeDto.setCreatedBy(Constants.USER_DEFAULT);
        return MappingDTO.getResponse(this.iEmployeeService.create(employeeDto));
    }

    @GetMapping
    public ResponseDTO<Object> findAll(){
        return MappingDTO.getResponse(this.iEmployeeService.findAll());
    }

    @GetMapping("/{idEmployee}")
    public ResponseDTO<Object> findById(@PathVariable("idEmployee") String id){
        return MappingDTO.getResponse(this.iEmployeeService.findById(id));
    }

    @PatchMapping("/{idEmployee}")
    public ResponseDTO<Object> update(@PathVariable("idEmployee") String id, @RequestBody EmployeeDTO employeeDto)
            throws ResourceNotFoundException, ValidationException {
        employeeDto.setIdEmployee(id);
        return MappingDTO.getResponse(this.iEmployeeService.update(employeeDto));
    }
}
