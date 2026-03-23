package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ProductSimpleVo {

    @Schema(description = "商品 ID")
    private Long id;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品描述")
    private String productDescription;

    @Schema(description = "商品价格")
    private Long productPrice;

    @Schema(description = "商品图片列表")
    private List<Map<String, Object>> images;
}