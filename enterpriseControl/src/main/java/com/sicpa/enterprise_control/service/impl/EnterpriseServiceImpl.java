package com.sicpa.enterprise_control.service.impl;

import com.sicpa.enterprise_control.dto.EnterpriseDTO;
import com.sicpa.enterprise_control.entity.Enterprise;
import com.sicpa.enterprise_control.exception.RequiredException;
import com.sicpa.enterprise_control.exception.ResourceNotFoundException;
import com.sicpa.enterprise_control.exception.ValidationException;
import com.sicpa.enterprise_control.repository.IEnterpriseRepository;
import com.sicpa.enterprise_control.service.IEnterpriseService;
import com.sicpa.enterprise_control.dto.util.MappingDTO;
import com.sicpa.enterprise_control.util.Constants;
import com.sicpa.enterprise_control.util.Messages;
import com.sicpa.enterprise_control.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

@Service
public class EnterpriseServiceImpl implements IEnterpriseService {
    @Autowired
    private IEnterpriseRepository iEnterpriseRepository;

    @Autowired
    private Map<String, ExecutorService> map;

    @Autowired
    private Map<String, Date> dateMap;

    @PostConstruct
    private void init() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            Date date = new Date();
            long millisecondsByDay = TimeUnit.DAYS.toMillis(1);
            this.dateMap.forEach((key, value) -> {
                if (date.getTime()-value.getTime() >= millisecondsByDay){
                    this.map.remove(key);
                    this.dateMap.remove(key);
                }
            });
        }, BigInteger.ZERO.intValue(), BigInteger.ONE.intValue(), TimeUnit.MINUTES);
    }

    @Override
    public EnterpriseDTO create(EnterpriseDTO enterpriseDTO) throws ValidationException {
        String requiredMessage = validateRequiredEnterprise(enterpriseDTO);
        if (Objects.nonNull(requiredMessage)){
            throw new RequiredException(requiredMessage);
        }
        enterpriseDTO.setAddress(enterpriseDTO.getAddress());
        enterpriseDTO.setPhone(this.validatePhoneNumber(enterpriseDTO.getPhone(),
                Messages.Errors.INVALID_PHONE.toString()));
        Enterprise enterprise = new Enterprise();
        enterprise = (Enterprise) MappingDTO.convertToEntity(enterpriseDTO, enterprise);
        EnterpriseDTO newEnterpriseDTO = new EnterpriseDTO();
        newEnterpriseDTO = (EnterpriseDTO) MappingDTO.convertToDto(
                iEnterpriseRepository.save(enterprise), newEnterpriseDTO);
        return newEnterpriseDTO;
    }

    @Override
    public EnterpriseDTO update(EnterpriseDTO enterpriseDTO) throws ExecutionException, InterruptedException {
        String id = enterpriseDTO.getIdEnterprise();
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss.SSS");
        System.out.println(" Executing Time for task name - "+id+" = " +ft.format(d));
        map.computeIfAbsent(id, v -> Executors.newSingleThreadExecutor());
        dateMap.computeIfAbsent(id, v -> Util.getCurrentDate());
        Future<EnterpriseDTO> returnedEnterprise = map.get(id).submit(() -> {
            EnterpriseDTO previousEnterprise = this.findById(id);
            if (Objects.isNull(previousEnterprise)) {
                throw new ResourceNotFoundException(Messages.NotFound.NOT_FOUND_ENTERPRISE.toString());
            } else {
                if (Objects.nonNull(enterpriseDTO.getName())) {
                    previousEnterprise.setName(enterpriseDTO.getName());
                }
                if (Objects.nonNull(enterpriseDTO.getAddress())) {
                    previousEnterprise.setAddress(enterpriseDTO.getAddress());
                }
                if (Objects.nonNull(enterpriseDTO.getPhone())) {
                    try {
                        previousEnterprise.setPhone(this.validatePhoneNumber(enterpriseDTO.getPhone(),
                                Messages.Errors.INVALID_PHONE.toString()));
                    } catch (ValidationException e) {
                        throw new ValidationException(e.getMessage());
                    }
                }
                previousEnterprise.setModifiedBy(Constants.USER_DEFAULT);
                previousEnterprise.setModifiedDate(Util.getCurrentDate());
            }
            Enterprise enterprise = new Enterprise();
            enterprise = (Enterprise) MappingDTO.convertToEntity(enterpriseDTO, enterprise);
            EnterpriseDTO updatedEnterpriseDTO = new EnterpriseDTO();
            updatedEnterpriseDTO = (EnterpriseDTO) MappingDTO.convertToDto(
                    iEnterpriseRepository.save(enterprise), updatedEnterpriseDTO);
            return updatedEnterpriseDTO;
        });
        return returnedEnterprise.get();
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

    private String validateRequiredEnterprise(EnterpriseDTO enterpriseDTO){
        if (Objects.isNull(enterpriseDTO.getName())){
            return Messages.Required.ENTERPRISE_NAME.toString();
        }
        if (Objects.isNull(enterpriseDTO.getAddress())){
            return Messages.Required.ENTERPRISE_ADDRESS.toString();
        }
        if (Objects.isNull(enterpriseDTO.getPhone())){
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
