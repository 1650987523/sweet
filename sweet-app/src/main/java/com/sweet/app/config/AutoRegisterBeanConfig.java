package com.sweet.app.config;

import com.sweet.security.config.SecurityPropertiesConfig;
import com.sweet.security.handler.GlobalExceptionHandler;
import com.sweet.security.utils.LoginPassWordUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
@Import(value = {SecurityPropertiesConfig.class})
public class AutoRegisterBeanConfig {

    @Bean
    public LoginPassWordUtil loginPassWordUtil(SecurityPropertiesConfig securityPropertiesConfig) {
        return new LoginPassWordUtil(securityPropertiesConfig);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
