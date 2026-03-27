package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 更新订单状态请求 DTO
 */
@Data
@Schema(description = "更新订单状态请求")
@Accessors(chain = true)
public class UpdateOrderStatusDto {

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "订单状态")
    private Integer orderStatus;

    @Schema(description = "状态修改原因")
    private String reason;

    @Schema(description = "审核原因")
    private String auditReason;
}
