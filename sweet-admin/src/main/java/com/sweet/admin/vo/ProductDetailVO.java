package com.sweet.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "商品详情 VO（admin 模块扩展）")
@EqualsAndHashCode(callSuper = true)
public class ProductDetailVO extends com.sweet.service.vo.ProductDetailVO {

    @Schema(description = "扩展字段（如有需要）")
    private String remark;
}