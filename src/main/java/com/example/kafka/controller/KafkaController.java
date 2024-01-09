package com.example.kafka.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.service.KafkaConsumer;
import com.example.kafka.service.KafkaProducer;

@RestController
@RequestMapping(path = "api/v1/kafka")
public class KafkaController {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaProducer kafkaProducer;

    private final KafkaConsumer kafkaConsumer;

    public KafkaController(KafkaProducer kafkaProducer, KafkaConsumer kafkaConsumer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaConsumer = kafkaConsumer;
    }

    // http://localhost:8080/api/v1/kafka/publish?message=Hello%20Wrld
    @GetMapping(path = "publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        logger.info(String.format("Message fetched '%s'", message));
        kafkaProducer.publish(message);
        return ResponseEntity.ok("Message publish successfully.");
    }

    // http://localhost:8080/api/v1/kafka/consume
    @GetMapping(path = "consume")
    public ResponseEntity<List<String>> getAllMessages() {
        List<String> messages = kafkaConsumer.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
