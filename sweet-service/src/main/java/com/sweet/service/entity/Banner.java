package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 轮播图实体
 */
@Data
@TableName(schema = "app", value = "banner")
@Accessors(chain = true)
public class Banner {

    @TableId(type = IdType.AUTO)
    @Schema(description = "轮播图 ID（自增主键）")
    protected Long id;

    @Schema(description = "门店 ID：0=所有门店通用，>0=特定门店")
    protected Long storeId = 0L;

    @Schema(description = "轮播图标题")
    protected String title;

    @Schema(description = "图片 URL")
    protected String imageUrl;

    @Schema(description = "跳转链接 URL")
    protected String linkUrl;

    @Schema(description = "跳转类型：1-商品 2-分类 3-活动 4-外链 5-页面")
    protected Integer linkType = 1;

    @Schema(description = "跳转参数（JSON 格式）")
    protected String linkParams;

    @Schema(description = "排序序号（越大越靠前）")
    protected Integer sortOrder = 0;

    @Schema(description = "状态：0-下架 1-上架")
    protected Integer status = 1;

    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected LocalDateTime startTime;

    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected LocalDateTime endTime;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    @Schema(description = "创建者")
    protected String createBy;

    @Schema(description = "更新者")
    protected String updateBy;
}
