package com.arun.Products.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Management APIs")
                        .version("1.0")
                        .description("Spring Boot + JWT + Role Based Authentication"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", 
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

    // Group User APIs
    @Bean
    public GroupedOpenApi userApis() {
        return GroupedOpenApi.builder()
                .group("User APIs")
                .pathsToMatch("/auth/**", "/products/**", "/user/cart/**")
                .build();
    }

    // Admin APIs
    @Bean
    public GroupedOpenApi adminApis() {
        return GroupedOpenApi.builder()
                .group("Admin APIs")
                .pathsToMatch("/products/**")
                .build();
    }
}
