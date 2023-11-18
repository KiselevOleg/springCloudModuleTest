package ru.example.cloudModuleTest.ProductDatabaseConsumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.Model.TestModel;

@Repository
public interface TestRepository extends CrudRepository<TestModel, Integer> {
}
