package com.gfg.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AppConfig configBean(){
        return new AppConfig();
    }
}