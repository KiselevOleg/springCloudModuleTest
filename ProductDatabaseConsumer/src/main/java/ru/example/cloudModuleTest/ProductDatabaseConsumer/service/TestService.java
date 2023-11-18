package ru.example.cloudModuleTest.ProductDatabaseConsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.Model.TestModel;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.repository.TestRepository;

import java.util.Date;

@Service
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(final TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    /**
     *
     * @param value input value
     */
    public void addTestModel(final Integer value) {
        final TestModel testModel = new TestModel(null, value, new Date());
        testRepository.save(testModel);
    }
}
