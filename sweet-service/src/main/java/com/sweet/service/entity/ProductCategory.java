package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName(schema = "app", value = "product_category")
@Accessors(chain = true)
public class ProductCategory {

    @TableId(type = IdType.AUTO)
    @Schema(description = "分类 ID（自增主键）")
    protected Long id;

    @Schema(description = "店铺 ID")
    protected Long storeId;

    @Schema(description = "父分类 ID（0 表示一级分类）")
    protected Integer parentId = 0;

    @Schema(description = "分类名称")
    protected String categoryName;

    @Schema(description = "分类图标")
    protected String icon;

    @Schema(description = "排序序号（数字越小越靠前）")
    protected Integer sort = 0;

    @Schema(description = "分类状态（1=启用，2=禁用）")
    protected Integer status = 1;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;
}
