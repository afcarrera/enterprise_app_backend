package com.sicpa.enterprise_control.config;

import com.sicpa.enterprise_control.dto.EnterpriseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
public class WebConfig {
    @Bean
    public Map<String, ConcurrentLinkedQueue<EnterpriseDTO>> enterpriceMap(){
        return new ConcurrentHashMap<>();
    }
}
