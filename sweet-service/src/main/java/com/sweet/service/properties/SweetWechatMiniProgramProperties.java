package com.sweet.service.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信小程序配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sweet.we-chat.mini-program")
@ConditionalOnProperty(prefix = "sweet.we-chat.mini-program", name = "enabled", matchIfMissing = true)
public class SweetWechatMiniProgramProperties {

    /**
     * 是否启用微信小程序
     */
    private boolean enabled = true;

    /**
     * 小程序 AppID
     */
    private String appId;

    /**
     * 小程序 AppSecret
     */
    private String appSecret;
}
