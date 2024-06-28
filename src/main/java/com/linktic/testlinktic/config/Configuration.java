package com.linktic.testlinktic.config;

import com.linktic.testlinktic.services.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    @Primary
    public TaskService taskService() {
        return new TaskService();
    }
}
