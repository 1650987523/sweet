package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "商品图片上传 DTO")
public class ProductUploadDto {

    @Schema(description = "门店 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long storeId;

    @Schema(description = "商品分类 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long productCategoryId;

    @Schema(description = "上传类型（main=商品主图，slider=商品轮播图）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "图片文件列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<MultipartFile> files;
}
