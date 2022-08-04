package com.example.demo.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "demo-api.openapi.enabled", havingValue = "true")
@OpenAPIDefinition(
        info = @Info(
                title = "Demo - Spring Boot App",
                description = "This is the REST-API for the Demo Spring Boot App."
        )
)
public class OpenApiConfiguration {
}
