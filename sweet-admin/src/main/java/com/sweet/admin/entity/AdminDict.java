package com.sweet.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "admin", value = "admin_dict")
@Schema(description = "数据字典表")
public class AdminDict {

    @TableId(type = IdType.AUTO)
    @Schema(description = "字典 ID（自增主键）")
    private Long id;

    @Schema(description = "字典类型标识（如：order_status, pay_type）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dictType;

    @Schema(description = "字典标签（显示文本）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dictLabel;

    @Schema(description = "字典值（存储值）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dictValue;

    @Schema(description = "排序序号", defaultValue = "0")
    private Integer sort = 0;

    @Schema(description = "标签样式（primary/success/warning/danger/info）")
    private String tagType;

    @Schema(description = "是否默认值", defaultValue = "false")
    private Boolean isDefault = false;

    @Schema(description = "状态（1=启用，2=禁用）", defaultValue = "1")
    private Integer status = 1;

    @Schema(description = "备注")
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
