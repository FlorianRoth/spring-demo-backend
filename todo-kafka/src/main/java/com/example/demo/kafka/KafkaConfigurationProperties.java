package com.example.demo.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "todo-kafka")
public class KafkaConfigurationProperties {

    private String topic;
}
