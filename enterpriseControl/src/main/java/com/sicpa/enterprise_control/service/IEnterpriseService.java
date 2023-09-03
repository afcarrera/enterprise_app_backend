package com.sicpa.enterprise_control.service;

import com.sicpa.enterprise_control.dto.EnterpriseDTO;
import com.sicpa.enterprise_control.exception.ValidationException;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface IEnterpriseService {
    EnterpriseDTO create(EnterpriseDTO enterpriseDTO) throws ValidationException;
    EnterpriseDTO update(EnterpriseDTO enterpriseDTO) throws
            ValidationException, ExecutionException, InterruptedException;
    Collection<EnterpriseDTO> findAll();
    EnterpriseDTO findById(String id);
}
