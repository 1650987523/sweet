package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "商品图片 DTO")
public class ProductImageDTO {

    @Schema(description = "图片 URL")
    private String url;

    @Schema(description = "排序序号（数字越小越靠前）")
    private Integer sort;

    @Schema(description = "图片描述")
    private String description;
}
