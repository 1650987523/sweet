package com.sweet.service.service.impl;

import cn.hutool.core.util.URLUtil;
import com.sweet.common.constant.AdminConstant;
import com.sweet.oss.config.CloudflarePropertiesConfiguration;
import com.sweet.service.service.CloudflareR2Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class CloudflareR2ServiceImpl implements CloudflareR2Service {

    private final S3Client s3Client;
    private final CloudflarePropertiesConfiguration cloudflarePropertiesConfiguration;

    @Override
    public PutObjectResponse putObject(String key, InputStream inputStream) {
        try {
            key = key.replaceAll("/+", "/");
            log.info("put object key:{}", key);

            long fileSize = inputStream.available();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(cloudflarePropertiesConfiguration.getS3().getBucketName())
                    .key(key)
                    .build();
            PutObjectResponse putObjectResponse = s3Client.putObject(request, RequestBody.fromInputStream(inputStream, fileSize));
            return putObjectResponse;
        }catch (Exception e){
            throw new RuntimeException("Failed to put object " + key + " in bucket " + cloudflarePropertiesConfiguration.getS3().getBucketName() + ": " + e.getMessage(), e);
        }

    }

    @Override
    public String generateUploadUrl(String key) {
        String publicUrl = cloudflarePropertiesConfiguration.getS3().getCustomDomain()
                + AdminConstant.PATH_SEPARATOR
                + key;
        return publicUrl.replaceAll("(?<!http:|https:)//+", "/");
    }

    @Override
    public DeleteObjectResponse  deleteObject(String key) {
        try {
            if(!StringUtils.hasText(key)){
                return null;
            }

            key = key.replaceAll("//", "/");

            if(key.startsWith("/")){
                key = key.substring(1);
            }
            log.info("delete object key:{}", key);

            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(cloudflarePropertiesConfiguration.getS3().getBucketName())
                    .key(key)
                    .build();
            DeleteObjectResponse deleteObjectResponse = s3Client.deleteObject(deleteObjectRequest);
            return deleteObjectResponse;
        }catch (S3Exception e){
            throw new RuntimeException("Failed to delete object " + key + " in bucket " + cloudflarePropertiesConfiguration.getS3().getBucketName() + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<PutObjectResponse> putBatchObjects(Map<String, InputStream> fileKeyToInputStream) {
        List<PutObjectResponse> responses = new ArrayList<>();

        try {

            if(CollectionUtils.isEmpty(fileKeyToInputStream)){
                return responses;
            }

            Set<String> keys = fileKeyToInputStream.keySet();
            for (String key : keys) {
                PutObjectResponse putObjectResponse = this.putObject(key, fileKeyToInputStream.get(key));
                responses.add(putObjectResponse);
            }

            return responses;
        }catch (Exception e){
            throw new RuntimeException("Failed to put batch objects in bucket " + cloudflarePropertiesConfiguration.getS3().getBucketName() + ": " + e.getMessage(), e);
        }
    }
}
