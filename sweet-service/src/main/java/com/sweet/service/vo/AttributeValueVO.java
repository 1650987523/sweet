package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性值 VO
 */
@Data
@Accessors(chain = true)
public class AttributeValueVO {

    @Schema(description = "属性值 ID")
    private Integer id;

    @Schema(description = "属性值名称")
    private String attributeValueName;

    @Schema(description = "关联属性 ID")
    private Integer attributeId;
}
