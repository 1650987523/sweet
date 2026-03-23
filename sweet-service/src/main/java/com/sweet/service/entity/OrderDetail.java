package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.sweet.service.dto.ProductImageDTO;
import com.sweet.service.vo.ProductSkuSpecVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "order_detail", autoResultMap = true)
public class OrderDetail {

    @TableId(type = IdType.AUTO)
    @Schema(description = "明细 ID（自增主键）")
    private Integer id;

    @Schema(description = "订单编号（关联 order_main 表的 order_no）")
    private String orderNo;

    @Schema(description = "商品 ID（关联 product 表的 id）")
    private Long productId;

    @Schema(description = "SKU ID（关联 product_sku 表的 id）")
    private Long skuId;

    @Schema(description = "商品名称（冗余字段）")
    private String productName;

    @Schema(description = "SKU 规格详情（JSON 格式）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, Object>> skuSpecs;

    @Schema(description = "商品单价（下单时的 SKU 售价，单位：分）")
    private Long price;

    @Schema(description = "购买数量")
    private Integer quantity = 1;

    @Schema(description = "小计金额（单价×数量，单位：分）")
    private Long subtotal;

    @Schema(description = "商品图片 URL（冗余字段）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, Object>> skuImages;

    @Schema(description = "商品项备注（针对单个商品的特殊要求）")
    private String itemRemark;

    @Schema(description = "创建时间（与订单主表创建时间一致）")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
