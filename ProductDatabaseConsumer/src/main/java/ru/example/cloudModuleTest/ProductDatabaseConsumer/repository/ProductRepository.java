package ru.example.cloudModuleTest.ProductDatabaseConsumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
