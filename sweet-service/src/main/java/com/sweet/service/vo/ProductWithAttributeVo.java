package com.sweet.service.vo;

import com.sweet.service.entity.Product;
import com.sweet.service.entity.ProductAttributeRelation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "商品信息及属性信息VO")
public class ProductWithAttributeVo {

    @Schema(description = "商品信息")
    private Product product;

    @Schema(description = "商品属性和商品属性值列表")
    private List<ProductAttributeWithValuesVo> attributes;
}
