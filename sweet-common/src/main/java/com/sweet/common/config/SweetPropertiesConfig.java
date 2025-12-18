package com.sweet.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sweet")
public class SweetPropertiesConfig {
    private Doc doc = new Doc();

    @Data
    public static class Doc {
        private String title = "Sweet API";
        private String version = "1.0";
        private String description = "Sweet API Documentation";
        private String contactName;
        private String contactEmail;
    }
}

