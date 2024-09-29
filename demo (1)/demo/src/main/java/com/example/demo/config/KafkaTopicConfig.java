package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static org.springframework.kafka.config.TopicBuilder.name;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic myProjectsTopic(){
        return TopicBuilder.name("myProjectsTopic")
                .build();
    }
}
