package com.sweet.service.vo;

import com.sweet.service.entity.ProductSku;
import com.sweet.service.entity.ProductSkuAttributeRelation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "商品 SKU VO（包含属性关联）")
@EqualsAndHashCode(callSuper = true)
public class ProductSkuVO extends ProductSku {

    @Schema(description = "SKU 属性关联列表")
    private List<ProductSkuAttributeRelation> attributeRelations;
}
