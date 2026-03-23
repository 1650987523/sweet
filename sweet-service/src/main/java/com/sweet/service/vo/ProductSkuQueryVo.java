package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "SKU信息查询请求 VO")
public class ProductSkuQueryVo {

    @Schema(description = "商品 ID")
    private Long productId;

    @Schema(description = "选中的规格列表")
    private List<SelectedSpecVo> sku;

    @Schema(description = "数量")
    private Integer count;

    @Data
    @Accessors(chain = true)
    @Schema(description = "选中的规格项")
    public static class SelectedSpecVo {
        @Schema(description = "属性 ID")
        private Long attributeId;

        @Schema(description = "属性值 ID")
        private Long attributeValueId;
    }
}
