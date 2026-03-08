package com.sweet.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "sweet.security")
public class SecurityPropertiesConfig {
    private String webAesSecret;
}
