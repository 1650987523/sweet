package com.sweet.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SweetPropertiesConfig.class)
@RequiredArgsConstructor
public class OpenApiConfig {

    private final SweetPropertiesConfig sweetPropertiesConfig;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(sweetPropertiesConfig.getDoc().getTitle())
                        .version(sweetPropertiesConfig.getDoc().getVersion())
                        .description(sweetPropertiesConfig.getDoc().getDescription())
                        .contact(new Contact()
                                .name(sweetPropertiesConfig.getDoc().getContactName())
                                .email(sweetPropertiesConfig.getDoc().getContactEmail())))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .name("BearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
