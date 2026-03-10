package com.sweet.oss.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

/**
 * 自动配置类,应用模块引入后自动加载对应功能
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(CloudflarePropertiesConfiguration.class)
public class CloudflareR2AutoConfiguration {

    private final CloudflarePropertiesConfiguration config;

    public CloudflareR2AutoConfiguration(CloudflarePropertiesConfiguration config) {
        this.config = config;
        log.info("CloudflareR2AutoConfiguration init :{}", config);
    }

    @Bean
    @ConditionalOnMissingBean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                config.getS3().getAccessKeyId(),
                config.getS3().getSecretAccessKey()
        );

        S3Configuration serviceConfiguration = S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .chunkedEncodingEnabled(false)
                .build();

        return S3Client.builder()
                .endpointOverride(URI.create(config.getS3().getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of("auto")) // Required by SDK but not used by R2
                .serviceConfiguration(serviceConfiguration)
                .build();
    }


}
