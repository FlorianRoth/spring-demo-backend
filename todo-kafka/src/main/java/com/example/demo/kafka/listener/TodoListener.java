package com.example.demo.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class TodoListener {

//    @KafkaListener(topics = "${todo-kafka.topic}")
//    public void listen(String data) {
//        log.info("Recieved Kafka message: {}", data);
//    }
}
