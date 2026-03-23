package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@TableName(schema = "app", value = "product_log")
@Schema(description = "商品操作日志实体")
public class ProductLog {

    @TableId(type = IdType.AUTO)
    @Schema(description = "日志 ID（自增主键）")
    private Long id;

    @Schema(description = "关联商品 ID")
    private Long productId;

    @Schema(description = "操作人 ID")
    private Long operatorId;

    @Schema(description = "操作人名称")
    private String operatorName;

    @Schema(description = "操作类型（如：创建、更新、删除、上下架）")
    private String action;

    @Schema(description = "操作内容描述")
    private String content;

    @Schema(description = "操作前数据（JSONB）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> beforeData;

    @Schema(description = "操作后数据（JSONB）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> afterData;

    @Schema(description = "操作 IP")
    private String ip;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
