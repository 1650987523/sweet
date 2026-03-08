package com.sweet.service.vo;

import com.sweet.service.entity.AttributeValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 属性及属性值 VO
 */
@Data
@Accessors(chain = true)
public class AttributeWithValueVO {

    @Schema(description = "属性 ID")
    private Long id;

    @Schema(description = "属性名称")
    private String name;

    @Schema(description = "属性类型 1=销售规格 2=商品参数")
    private Integer type;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "属性值列表")
    private List<AttributeValue> values;
}
