package com.sweet.service.vo;

import com.sweet.service.entity.ProductSku;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "商品详情 VO（包含商品、SKU）")
public class ProductDetailVO {

    @Schema(description = "商品 ID")
    private Long id;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品描述")
    private String description;

    @Schema(description = "商品图片列表")
    private List<ProductImageVO> images;

    @Schema(description = "门店 ID")
    private Long storeId;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "商品 SKU 列表")
    private List<ProductSku> skus;
}
