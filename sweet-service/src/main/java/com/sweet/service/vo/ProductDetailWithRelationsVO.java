package com.sweet.service.vo;

import com.sweet.service.entity.Product;
import com.sweet.service.entity.ProductAttributeRelation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "商品详情 VO（包含商品、属性关联、SKU 及 SKU 属性关联）")
public class ProductDetailWithRelationsVO {

    @Schema(description = "商品信息")
    private Product product;

    @Schema(description = "商品属性关联列表")
    private List<ProductAttributeRelation> attributeRelations;

    @Schema(description = "商品 SKU 列表（包含属性关联）")
    private List<ProductSkuVO> skus;
}
