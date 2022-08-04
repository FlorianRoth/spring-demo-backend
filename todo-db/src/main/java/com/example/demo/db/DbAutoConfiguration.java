package com.example.demo.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EntityScan
@Slf4j
public class DbAutoConfiguration {

    public DbAutoConfiguration() {
        log.info("Auto-configuring Demo Database");
    }
}
