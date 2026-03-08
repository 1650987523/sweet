package com.sweet.admin.config;

import com.sweet.security.config.LogWebMvcConfig;
import com.sweet.security.config.SecurityPropertiesConfig;
import com.sweet.security.handler.GlobalExceptionHandler;
import com.sweet.security.utils.LoginPassWordUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@AllArgsConstructor
@Import(value = {LogWebMvcConfig.class, SaTokenConfig.class, SecurityPropertiesConfig.class})
public class AutoRegisterBeanConfig {

    @Bean
    public LoginPassWordUtil loginPassWordUtil(SecurityPropertiesConfig securityPropertiesConfig) {
        return new LoginPassWordUtil(securityPropertiesConfig);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();

    }
}
