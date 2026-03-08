package com.sweet.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "商品 SKU DTO（用于创建/更新商品）")
@EqualsAndHashCode(callSuper = true)
public class ProductSkuDTO extends com.sweet.service.dto.ProductSkuDTO {

}