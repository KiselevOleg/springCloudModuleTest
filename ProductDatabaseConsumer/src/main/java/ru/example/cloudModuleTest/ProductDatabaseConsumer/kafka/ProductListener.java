package ru.example.cloudModuleTest.ProductDatabaseConsumer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.Model.Product;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.service.ProductService;

@Service
@SuppressWarnings("DesignForExtension")
public class ProductListener {
    private final ProductService productService;

    @Autowired
    public ProductListener(final ProductService productService) {
        this.productService = productService;
    }

    //@KafkaListener(topics = "${application.kafka.topic}", id = "${spring.kafka.consumer.group-id}")
    @KafkaListener(topics = "product-topic", groupId = "product-database-consumer")
    public void listenGroup(final String message) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final Product product = objectMapper.readValue(message, Product.class);
            productService.addProduct(product);
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
