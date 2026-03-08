package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性选项 VO（用于下拉选择）
 */
@Data
@Accessors(chain = true)
public class AttributeOptionVO {

    @Schema(description = "属性 ID")
    private Long id;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性类型 1=销售规格 2=商品参数")
    private Integer type;
}
