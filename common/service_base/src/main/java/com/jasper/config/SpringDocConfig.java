package com.jasper.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author com
 * 6/16/2023  1:44 PM
 */
@Configuration
public class SpringDocConfig {
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("com mall API")
                        .description("com mall application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("com mall Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
