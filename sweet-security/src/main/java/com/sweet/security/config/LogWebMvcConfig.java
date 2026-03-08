package com.sweet.security.config;

import com.sweet.security.Interceptor.LogInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 日志打印拦截器
 */
public class LogWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        // 放行所有静态资源目录
                        "/js/**", "/css/**", "/images/**", "/fonts/**", "/icons/**",
                        // 放行静态资源文件后缀
                        "/*.html", "/*.js", "/*.css", "/*.png", "/*.jpg", "/*.jpeg", "/*.gif", "/*.ico",
                        // 放行 Swagger/OpenAPI 文档（如果需要，可选）
                        "/swagger-ui/**", "/v3/api-docs/**"
                )
                .order(0);
    }
}
