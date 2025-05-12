package com.plazacomidas.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Usuarios - Plaza Comidas")
                        .version("v1.0.0")
                        .description("Microservicio para gestión de usuarios")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("soporte@plazacomidas.com")));
    }
}
