package com.sweet.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "商品 DTO（创建/更新共用）")
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends com.sweet.service.dto.ProductDTO {

}