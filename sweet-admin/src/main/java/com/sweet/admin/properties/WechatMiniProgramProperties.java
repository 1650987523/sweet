package com.sweet.admin.properties;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "sweet.we-chat.mini-program")
public class WechatMiniProgramProperties {
    private String appId;
    private String appSecret;
    private String code2session;

    @PostConstruct
    public void init() {
        log.info("WechatMiniProgramProperties loaded:{}", this);
    }
}
