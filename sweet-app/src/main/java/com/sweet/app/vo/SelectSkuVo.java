package com.sweet.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "选择 SKU 请求 VO")
public class SelectSkuVo {

    @Schema(description = "商品 ID")
    private Long productId;

    @Schema(description = "SKU ID")
    private Long skuId;
}
