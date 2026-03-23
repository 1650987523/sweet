package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "商品及分类 VO")
@Accessors(chain = true)
public class ProductWithCategoryVo {

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "该分类下的商品列表")
    private List<ProductSimpleVo> products = new ArrayList<>();
}