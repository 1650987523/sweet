package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "attribute_value")
public class AttributeValue {

    @TableId(type = IdType.AUTO)
    @Schema(description = "属性值 ID（自增主键）")
    private Long id;

    @Schema(description = "属性 ID（对应 attribute 表的 id）")
    private Long attributeId;

    @Schema(description = "属性值（如：标准 7分糖）")
    private String value;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态 0=禁用 1=正常")
    private Integer status;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Schema(description = "创建者")
    private Long createBy;

    @Schema(description = "更新者")
    private Long updateBy;
}
