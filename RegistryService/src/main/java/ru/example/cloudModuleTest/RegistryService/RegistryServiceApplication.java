package ru.example.cloudModuleTest.RegistryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@SuppressWarnings("HideUtilityClassConstructor")
public class RegistryServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RegistryServiceApplication.class, args);
    }

}
