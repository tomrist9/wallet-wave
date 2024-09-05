package com.gfg.demo.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollegeConfig {

    // Using Bean annotation to create
    // College class Bean
    @Bean
    // Here the method name is the
    // bean id/bean name
    public College collegeBean() {

        // Return the College object
        return new College();
    }

}