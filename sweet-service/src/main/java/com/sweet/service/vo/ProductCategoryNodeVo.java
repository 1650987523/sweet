package com.sweet.service.vo;

import com.sweet.service.entity.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "商品分类树形结构VO")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductCategoryNodeVo extends ProductCategory {

    @Schema(description = "子分类列表（树形结构关键）")
    private List<ProductCategoryNodeVo> children = new ArrayList<>();

    @Schema(description = "是否展开节点（前端展示用）")
    private Boolean expand = false;

    @Schema(description = "是否有子节点（前端展示用）")
    private Boolean hasChildren = false;
}