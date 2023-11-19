package ru.example.cloudModuleTest.ProductProducer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     *
     * @param topic
     * @param message
     */
    public void send(final String topic, final String message) {
        kafkaTemplate.send(topic, message);
    }
}
