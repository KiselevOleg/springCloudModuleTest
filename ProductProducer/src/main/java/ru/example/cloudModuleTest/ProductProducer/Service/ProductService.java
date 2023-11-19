package ru.example.cloudModuleTest.ProductProducer.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.ProductProducer.kafka.Producer;
import ru.example.cloudModuleTest.ProductProducer.model.Product;

@Service
public class ProductService {
    private Producer producer;

    @Autowired
    public ProductService(final Producer producer) {
        this.producer = producer;
    }

    @Value(value = "${application.kafka.topic}")
    private String nameTopic;

    /**
     *
     * @param product input
     * @return successful
     */
    public Boolean addProductToKafka(final Product product) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final String json = objectMapper.writeValueAsString(product);
            producer.send(nameTopic, json);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
