package ru.example.cloudModuleTest.ProductConsoleConsumer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("DesignForExtension")
public class ProductListener {
    static final Logger log = LoggerFactory.getLogger(ProductListener.class);

    @KafkaListener(topics = "product-topic", groupId = "product-console-consumer")
    public void listenGroup(final String message) {
        log.info("new product " + message);
    }
}
