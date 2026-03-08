package com.sweet.admin.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.stp.StpLogic;
import com.sweet.admin.Interceptor.ApiPermissionInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Sa-Token 登录验证拦截器
        registry.addInterceptor(new SaInterceptor(handler ->
            log.info("sa-token 权限拦截")
        )).addPathPatterns("/**").excludePathPatterns("/admin/user/login").excludePathPatterns("/admin/user/info");

        // 接口权限拦截器（从数据库动态加载权限配置）
//        registry.addInterceptor(new ApiPermissionInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/admin/user/login", "/admin/user/info");
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }
}
