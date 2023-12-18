package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.example.cloudModuleTest.GeocoderService")
public class JpaConfig {
}
