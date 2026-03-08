package com.sweet.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "SKU 规格项")
public class ProductSkuSpecVO {

    @Schema(description = "属性 ID")
    @JsonProperty("attrId")
    private Long attrId;

    @Schema(description = "属性名")
    @JsonProperty("attrName")
    private String attrName;

    @Schema(description = "属性值 ID")
    @JsonProperty("attrValueId")
    private Long attrValueId;

    @Schema(description = "属性值名称")
    @JsonProperty("value")
    private String value;
}