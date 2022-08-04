package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ComponentScan
//@EnableKafka
@EnableConfigurationProperties(KafkaConfigurationProperties.class)
@Slf4j
public class KafkaAutoConfiguration {

    public KafkaAutoConfiguration() {
        log.info("Auto-configuring Demo Kafka");
    }

//    @Bean
//    public NewTopic todoTopic(KafkaConfigurationProperties config) {
//        return TopicBuilder.name(config.getTopic())
//                .build();
//    }
}
