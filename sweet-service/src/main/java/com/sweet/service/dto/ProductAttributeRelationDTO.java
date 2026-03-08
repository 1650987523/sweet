package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品属性关联 DTO
 */
@Data
@Accessors(chain = true)
@Schema(description = "商品属性关联 DTO")
public class ProductAttributeRelationDTO {

    @Schema(description = "ID（更新时必填，创建时忽略）")
    private Long id;

    @Schema(description = "属性模板 ID（关联 attribute 表）")
    private Long attributeId;

    @Schema(description = "属性值 ID（关联 attribute_value 表）")
    private Long attributeValueId;

    @Schema(description = "是否必填")
    private Integer required;

    @Schema(description = "排序序号")
    private Integer sort = 0;
}
