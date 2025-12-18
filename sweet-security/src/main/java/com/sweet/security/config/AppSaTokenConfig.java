package com.sweet.security.config;

import com.sweet.security.Interceptor.AppSaTokenInterceptor;import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppSaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppSaTokenInterceptor())
                .addPathPatterns("/app/**");
    }}
