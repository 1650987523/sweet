package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "商品 DTO（创建/更新共用）")
public class ProductDTO {

    @Schema(description = "商品 ID（更新时必填，创建时忽略）")
    private Long id;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品副标题（简短描述）")
    private String subTitle;

    @Schema(description = "商品描述")
    private String description;

    @Schema(description = "商品详细描述（HTML）")
    private String detail;

    @Schema(description = "商品图片列表")
    private List<ProductImageDTO> images;

    @Schema(description = "商品轮播图列表")
    private List<ProductImageDTO> sliderImages;

    @Schema(description = "门店 ID")
    private Long storeId;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "售价（单位：分）")
    private Long price;

    @Schema(description = "市场价（单位：分）")
    private Long marketPrice;

    @Schema(description = "会员价（单位：分）")
    private Long memberPrice;

    @Schema(description = "成本价（单位：分）")
    private Long costPrice;

    @Schema(description = "库存数量")
    private Integer stock;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "商品标签（JSON 数组）")
    private List<String> tags;

    @Schema(description = "排序序号（数字越小越靠前）")
    private Integer sort;

    @Schema(description = "是否热卖（0=否，1=是）")
    private Boolean isHot;

    @Schema(description = "是否推荐（0=否，1=是）")
    private Boolean isRecommend;

    @Schema(description = "是否新品（0=否，1=是）")
    private Boolean isNew;

    @Schema(description = "商品状态（0=禁用，1=正常，2=下架）")
    private Integer status;

    @Schema(description = "商品属性关联列表")
    private List<ProductAttributeRelationDTO> attributeRelations;

    @Schema(description = "商品 SKU 列表")
    private List<ProductSkuDTO> skus;
}