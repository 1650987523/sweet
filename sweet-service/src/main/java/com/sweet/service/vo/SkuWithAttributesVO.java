package com.sweet.service.vo;

import com.sweet.service.entity.ProductSku;
import com.sweet.service.entity.ProductSkuAttributeRelation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "带属性关联的 SKU VO")
public class SkuWithAttributesVO {

    @Schema(description = "SKU 信息")
    private ProductSku sku;

    @Schema(description = "SKU 属性关联列表")
    private List<ProductSkuAttributeRelation> attributeRelations;
}
