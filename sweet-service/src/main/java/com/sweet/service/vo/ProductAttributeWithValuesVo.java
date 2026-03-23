package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ProductAttributeWithValuesVo {

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "属性ID")
    private Long attributeId;

    @Schema(description = "属性名称")
    private String attributeName;

    @Schema(description = "属性值列表")
    private List<ProductAttributeValueVo> attributeValues;

    @Schema(description = "是否必填")
    private Boolean required;

    @Schema(description = "排序")
    private Integer sort = 0;
}
