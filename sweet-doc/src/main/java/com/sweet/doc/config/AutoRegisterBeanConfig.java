package com.sweet.doc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OpenApiConfig.class})
public class AutoRegisterBeanConfig {
}
