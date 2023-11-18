package ru.example.cloudModuleTest.ProductDatabaseConsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.cloudModuleTest.ProductDatabaseConsumer.service.TestService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("test")
public class TestController {
    private final TestService testService;

    @Autowired
    TestController(final TestService testService) {
        this.testService = testService;
    }

    /**
     *
     * @param value input value
     * @return Http status
     */
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testDatabase(final @RequestParam Integer value) {
        try {
            testService.addTestModel(value);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
