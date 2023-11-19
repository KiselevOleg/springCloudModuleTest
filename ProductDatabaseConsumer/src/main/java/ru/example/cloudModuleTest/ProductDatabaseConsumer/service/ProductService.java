package ru.example.cloudModuleTest.ProductDatabaseConsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.Model.Product;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     *
     * @param product product object
     */
    public void addProduct(final Product product) {
        productRepository.save(product);
    }
}
