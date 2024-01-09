package com.example.kafka.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "MyTopic1", groupId = "my-group")
    public void consume(String message) {
        logger.info(String.format("Message consumed '%s'", message));
        messages.add(message);
    }

    public List<String> getAllMessages() {
        return new ArrayList<>(messages);
    }

}
