package com.sweet.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "商品分类选项 VO")
public class ProductCategoryOptionVo {

    @Schema(description = "分类 ID")
    private Integer id;

    @Schema(description = "分类名称")
    private String name;
}
