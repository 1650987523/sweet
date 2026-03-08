package com.sweet.oss.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * r2储存对应所需字段
 */
@Data
@ConfigurationProperties(prefix = "sweet.oss")
public class CloudflarePropertiesConfiguration {

    private S3Config s3 = new S3Config();

    @Data
    public static class S3Config{
        private String accountId;
        private String accessKeyId;
        private String secretAccessKey;
        private String bucketName;
        private String endpoint;
        private String customDomain;
    }
}
