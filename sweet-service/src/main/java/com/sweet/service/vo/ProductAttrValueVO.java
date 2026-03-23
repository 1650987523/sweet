package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "属性值项 VO")
public class ProductAttrValueVO {

    @Schema(description = "属性值 ID")
    private Integer id;

    @Schema(description = "属性值名称")
    private String value;

    @Schema(description = "是否默认值")
    private Boolean isDefault = false;

    @Schema(description = "排序")
    private Integer sort = 0;
}