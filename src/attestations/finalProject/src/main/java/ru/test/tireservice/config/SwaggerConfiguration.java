package ru.test.tireservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI tireOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tire Service APIv")
                        .description("API сервиса шиномонтажа для управления автомобилями клиентов и записями на обслуживание")
                        .version("v1"));
    }
}
