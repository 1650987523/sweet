package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "商品图片 VO")
public class ProductImageVO {

    @Schema(description = "图片 URL")
    private String url;

    @Schema(description = "排序序号（数字越小越靠前）")
    private Integer sort;
}
