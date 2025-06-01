package br.com.l2code.aulas.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        var openApi = new OpenAPI().info(getInfo());

        return openApi;
    }

    private Info getInfo() {
        return new Info()
                .title("Avaliação Técnica Java L2 Code - Exercício 2 - Horários de aula")
                .version("1.0.0")
                .contact(new Contact()
                        .email("emersondiaspd@gmail.com")
                        .name("Emerson Dias de Oliveira"))
                .license(new License()
                        .name("Unlicense")
                        .url("https://unlicense.org/"));
    }

}