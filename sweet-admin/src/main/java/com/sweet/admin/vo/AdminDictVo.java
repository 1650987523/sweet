package com.sweet.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据字典项 VO
 */
@Data
@Accessors(chain = true)
@Schema(description = "数据字典项 VO")
public class AdminDictVo {

    @Schema(description = "字典 ID")
    private Long id;

    @Schema(description = "字典标签（显示文本）")
    private String dictLabel;

    @Schema(description = "字典值（存储值）")
    private String dictValue;

    @Schema(description = "排序序号")
    private Integer sort;

    @Schema(description = "标签样式")
    private String tagType;

    @Schema(description = "是否默认值")
    private Boolean isDefault;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
