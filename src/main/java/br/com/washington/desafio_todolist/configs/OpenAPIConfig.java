package br.com.washington.desafio_todolist.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("Desafio TODO List")
                .version("v1")
                .description("CRUD em Spring Boot")
                .termsOfService("/swagger-ui/index.html")
                .license(new License().name("Apache 2.0").url("/swagger-ui/index.html"))
                ).externalDocs(new ExternalDocumentation());
    }
}
