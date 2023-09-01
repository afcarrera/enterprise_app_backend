package com.sicpa.enterprise_control.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Configuration
public class WebConfig {
    @Bean
    public Map<String, ExecutorService> enterpriseMap(){
        return new ConcurrentHashMap<>();
    }
}
