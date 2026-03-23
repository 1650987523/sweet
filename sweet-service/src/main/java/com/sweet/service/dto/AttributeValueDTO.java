package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性值 DTO
 */
@Data
@Accessors(chain = true)
@Schema(description = "属性值 DTO")
public class AttributeValueDTO {

    @Schema(description = "属性值 ID，更新时需要")
    private Long id;

    @Schema(description = "属性值名称")
    private String value;

    @Schema(description = "关联属性 ID")
    private Long attributeId;

    @Schema(description = "排序")
    private Integer sort = 0;

    @Schema(description = "状态 0=禁用 1=正常")
    private Integer status = 1;
}
