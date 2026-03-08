package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 商品属性模板表
 */
@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "attribute")
public class Attribute {

    @TableId(type = IdType.AUTO)
    @Schema(description = "属性 ID（自增主键）")
    private Long id;

    @Schema(description = "属性名称（如：辣度、甜度、温度、杯型）")
    private String name;

    @Schema(description = "属性类型 1=销售规格 2=商品参数")
    private Integer type;

    @Schema(description = "门店 ID")
    private Long storeId;

    @Schema(description = "状态 0=禁用 1=正常")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

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
