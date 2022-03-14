package com.example.demo.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@Slf4j
public class CoreAutoConfiguration {

    public CoreAutoConfiguration() {
        log.info("Auto-configuring Demo Core");
    }
}
