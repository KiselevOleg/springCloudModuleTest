package ru.example.cloudModuleTest.ConfigService.CloudModuleTestConfigService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@SuppressWarnings("HideUtilityClassConstructor")
public class CloudModuleTestConfigServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CloudModuleTestConfigServiceApplication.class, args);
    }

}
