package com.sweet.service.service;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface CloudflareR2Service {

    /**
     * 上传文件
     * @param key(这个是文件的路径和名称,sdk中参数是这样设置的所以不叫path叫key)
     * @param inputStream
     * @return
     */
    PutObjectResponse putObject(String key, InputStream inputStream);

    /**
     * 生成文件上传后可访问url
     * @param key
     * @return
     */
    String generateUploadUrl(String key);

    /**
     * 删除文件
     * @param key
     * @return
     */
    DeleteObjectResponse deleteObject(String key);

    /**
     * 批量上传文件
      * @param fileKeyToInputStream 输入流
     * @return
     */
    List<PutObjectResponse> putBatchObjects(Map<String, InputStream> fileKeyToInputStream);
}
