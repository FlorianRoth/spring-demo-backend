package com.example.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@Slf4j
public class ApiAutoConfiguration {

    public ApiAutoConfiguration() {
        log.info("Auto-configuring Demo API");
    }
}
