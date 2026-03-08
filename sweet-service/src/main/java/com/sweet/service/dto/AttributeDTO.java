package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 属性 DTO（用于创建/更新属性，包含属性值列表）
 */
@Data
@Accessors(chain = true)
public class AttributeDTO {

    @Schema(description = "属性 ID，更新时需要")
    private Long id;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性类型 1=销售规格 2=商品参数")
    private Integer type;

    @Schema(description = "状态 0=禁用 1=正常")
    private Integer status = 1;

    @Schema(description = "排序")
    private Integer sort = 0;

    @Schema(description = "门店ID")
    private Long storeId;

    @Schema(description = "属性值列表")
    private List<AttributeValueDTO> values;
}
