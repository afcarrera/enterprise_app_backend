package com.sicpa.enterprise_control.service.impl;

import com.sicpa.enterprise_control.dto.EnterpriseDTO;
import com.sicpa.enterprise_control.entity.Enterprise;
import com.sicpa.enterprise_control.repository.IEnterpriseRepository;
import com.sicpa.enterprise_control.service.IEnterpriseService;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseServiceImpl implements IEnterpriseService {
    @Autowired
    private IEnterpriseRepository iEnterpriseRepository;

    @Override
    public EnterpriseDTO create(EnterpriseDTO enterpriseDTO) {
        Enterprise enterprise = new Enterprise();
        enterprise = (Enterprise) MappingDTO.convertToEntity(enterpriseDTO, enterprise);
        EnterpriseDTO newEnterpriseDTO = new EnterpriseDTO();
        newEnterpriseDTO = (EnterpriseDTO) MappingDTO.convertToDto(
                iEnterpriseRepository.save(enterprise), newEnterpriseDTO);
        return newEnterpriseDTO;
    }

    @Override
    public EnterpriseDTO update(EnterpriseDTO enterpriseDTO) {
        Enterprise enterprise = new Enterprise();
        enterprise = (Enterprise) MappingDTO.convertToEntity(enterpriseDTO, enterprise);
        EnterpriseDTO updatedEnterpriseDTO = new EnterpriseDTO();
        updatedEnterpriseDTO = (EnterpriseDTO) MappingDTO.convertToDto(
                iEnterpriseRepository.save(enterprise), updatedEnterpriseDTO);
        return updatedEnterpriseDTO;
    }

    @Override
    public Collection<EnterpriseDTO> findAll() {
        List<Enterprise> allEnterprises = iEnterpriseRepository.findAll();
        List<EnterpriseDTO> enterpriseDTOS  = new ArrayList<>();
        EnterpriseDTO enterpriseDTO;
        for (Enterprise enterprise : allEnterprises){
            enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO = (EnterpriseDTO) MappingDTO.convertToDto(
                    enterprise, enterpriseDTO);
            enterpriseDTOS.add(enterpriseDTO);
        }
        return enterpriseDTOS;
    }

    @Override
    public EnterpriseDTO findById(String id) {
        Enterprise enterpriseById = findEnterpriseById(id);
        if (enterpriseById == null){
            return null;
        }
        EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
        enterpriseDTO = (EnterpriseDTO) MappingDTO.convertToDto(
                enterpriseById, enterpriseDTO);
        return enterpriseDTO;
    }

    private Enterprise findEnterpriseById(String id){
        Optional<Enterprise> enterpriseOptional = iEnterpriseRepository.findById(id);
        return enterpriseOptional.orElse(null);
    }
}
