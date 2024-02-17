package com.luisgarcia.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI uiConfig() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info()
                .title("Participante API")
                .version("1.0")
                .description(
                    "Projeto CRUD simples para gerenciamento de participantes utilizando Spring Boot 3 e Java 17.")
                .contact(new Contact().name("Luis Garcia").url("https://github.com/luisgarcia173/")));
  }

}
