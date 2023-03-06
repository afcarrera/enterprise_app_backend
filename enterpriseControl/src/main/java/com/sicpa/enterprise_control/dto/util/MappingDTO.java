package com.sicpa.enterprise_control.dto.util;

import com.sicpa.enterprise_control.dto.IDTOEntity;
import com.sicpa.enterprise_control.dto.ResponseDTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MappingDTO {

	private MappingDTO(){}

    public static IDTOEntity convertToDto(Object obj, IDTOEntity dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(obj, dto.getClass());
    }

    public static Object convertToEntity(IDTOEntity dto, Object obj) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, obj.getClass());
    }

    public static ResponseDTO<Object> getResponse(Object obj){
        ResponseDTO<Object> response = new ResponseDTO<>();
        response.setData(obj);
        return response;
    }
}
