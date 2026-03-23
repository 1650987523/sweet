package com.sweet.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MybatisPlusConfig.class})
@ComponentScan(basePackages = {"com.sweet.service.service","com.sweet.service.properties"})
@MapperScan(basePackages = {"com.sweet.service.mapper"})
public class AutoRegisterBeanConfig {

}
