package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "商品状态修改请求参数")
public class ProductStatusDto {

    @Schema(description = "商品 ID", example = "1")
    private Long id;

    @Schema(description = "商品状态 0=禁用 1=正常 2=下架", example = "1")
    private Integer status;
}
