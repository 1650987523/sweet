package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "商品属性值 VO")
public class ProductAttributeValueVo {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "商品属性ID")
    private Long attributeId;

    @Schema(description = "商品属性值")
    private String value;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态")
    private Boolean status;
}
