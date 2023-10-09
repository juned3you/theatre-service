package com.xyz.theatreService.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI theatreMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Movie Booking System")
                        .description("Test Swagger API documentation")
                        .version("1.0"));
    }
}
