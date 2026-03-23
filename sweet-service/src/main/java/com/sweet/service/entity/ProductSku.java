package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweet.service.vo.ProductSkuSpecVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "product_sku", autoResultMap = true)
public class ProductSku {

    @TableId(type = IdType.AUTO)
    @Schema(description = "SKU ID（自增主键）")
    private Long id;

    @Schema(description = "关联商品 ID")
    private Long productId;

    @Schema(description = "SKU 规格定义（JSON 格式）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, Object>> specs;

    @Schema(description = "售价")
    private Long price;

    @Schema(description = "库存数量")
    private Integer stock = -1;

    @Schema(description = "销量")
    private Integer sales = 0;

    @Schema(description = "SKU 图片列表（JSONB）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, Object>> images;

    @Schema(description = "状态")
    private Integer status = 1;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
