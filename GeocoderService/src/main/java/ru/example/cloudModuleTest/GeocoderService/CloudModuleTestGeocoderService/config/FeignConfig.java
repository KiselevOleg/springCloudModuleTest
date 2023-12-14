package ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "ru.example.cloudModuleTest.GeocoderService.CloudModuleTestGeocoderService")
public class FeignConfig {
}
