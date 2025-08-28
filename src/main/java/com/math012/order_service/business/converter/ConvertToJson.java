package com.math012.order_service.business.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConvertToJson {

    private final ObjectMapper objectMapper;

    public String convertToJson(Object dto){
        try {
            return objectMapper.writeValueAsString(dto);
        }catch (Exception e){
            return "error while convert to json: " + e.getMessage();
        }
    }

    public <T> T readJson(String jsonString, Class<T> classConverter){
        try {
            return objectMapper.readValue(jsonString,classConverter);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
