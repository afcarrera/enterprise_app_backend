package com.sicpa.enterprise_control.service;

import com.sicpa.enterprise_control.dto.EnterpriseDTO;

import java.util.Collection;

public interface IEnterpriseService {
    EnterpriseDTO create(EnterpriseDTO enterpriseDTO);
    EnterpriseDTO update(EnterpriseDTO enterpriseDTO);
    Collection<EnterpriseDTO> findAll();
    EnterpriseDTO findById(String id);
}
