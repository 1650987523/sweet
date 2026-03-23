package com.sweet.service.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sweet.we-chat.pay")
@ConditionalOnProperty(prefix = "sweet.we-chat.pay", name = "enabled", matchIfMissing = true)
public class SweetWechatPayProperties {

    /**
     * 是否启用微信支付
     */
    private boolean enabled = true;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户私钥
     */
    private String privateKey;

    /**
     * 商户证书序列号
     */
    private String certificateSerialNo;

    /**
     * API v3 密钥
     */
    private String apiV3Key;
}
