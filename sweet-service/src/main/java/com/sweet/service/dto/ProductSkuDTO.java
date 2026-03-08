package com.sweet.service.dto;

import com.sweet.service.vo.ProductSkuSpecVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "商品 SKU DTO（用于创建/更新商品）")
public class ProductSkuDTO {

    @Schema(description = "SKU ID（更新时必填，创建时忽略）")
    private Long id;

    @Schema(description = "SKU 规格列表")
    private List<ProductSkuSpecVO> specs;

    @Schema(description = "售价（单位：分）")
    private Long price;

    @Schema(description = "库存数量")
    private Integer stock;

    @Schema(description = "SKU 图片列表")
    private List<ProductImageDTO> images;

    @Schema(description = "状态 0=禁用 1=正常 2=售罄")
    private Integer status;

    @Schema(description = "SKU 属性关联列表")
    private List<ProductSkuAttributeRelationDTO> attributeRelations;
}