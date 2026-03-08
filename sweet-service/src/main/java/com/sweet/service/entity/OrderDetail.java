package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@TableName(schema = "app", value = "order_detail")
public class OrderDetail {

    @TableId(type = IdType.AUTO)
    @Schema(description = "明细ID（自增主键）", example = "7001", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "订单编号（关联 order_main 表的 order_no）", example = "ORD2025122210300001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderNo;

    @Schema(description = "商品ID（关联 product 表的 id）", example = "1001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer productId;

    @Schema(description = "SKU ID（关联 product_sku 表的 id）", example = "2001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer skuId;

    @Schema(description = "商品名称（冗余字段）", example = "珍珠奶茶", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productName;

    @Schema(description = "SKU标题（规格组合描述，冗余字段）", example = "珍珠奶茶-全糖-正常冰-中杯", requiredMode = Schema.RequiredMode.REQUIRED)
    private String skuName;

    @Schema(description = "SKU规格详情（JSON格式，如：{\"甜度\":\"全糖\",\"冰度\":\"正常冰\",\"杯型\":\"中杯\"}）",
            example = "{\"甜度\":\"全糖\",\"冰度\":\"正常冰\",\"杯型\":\"中杯\"}")
    @TableField(typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class) // JSONB 类型处理器（MyBatis-Plus 3.5+ 内置）
    private Map<String, Object> skuSpec;

    @Schema(description = "商品单价（下单时的SKU售价，单位：分）", example = "1500", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long price;

    @Schema(description = "购买数量", example = "2", defaultValue = "1")
    private Integer quantity = 1;

    @Schema(description = "小计金额（单价×数量，单位：分）", example = "3000", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long subtotal;

    @Schema(description = "商品图片URL（冗余字段）", example = "https://xxx-product-img.com/1001-full-sugar.jpg")
    private String productImage;

    @Schema(description = "商品项备注（针对单个商品的特殊要求，如：少糖、去冰）", example = "少糖，打包")
    private String itemRemark;

    @Schema(description = "创建时间（与订单主表创建时间一致）", example = "2025-12-22T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
