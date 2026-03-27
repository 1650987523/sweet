package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 退款申请请求 DTO
 */
@Data
@Schema(description = "退款申请请求 DTO")
public class ApplyRefundDto {

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "用户 ID")
    private Integer userId;

    @Schema(description = "门店 ID")
    private Integer storeId;

    @Schema(description = "订单总金额（单位：分）")
    private Long orderAmount;

    @Schema(description = "申请退款金额（单位：分）")
    private Long refundAmount;

    @Schema(description = "退款类型（1=门店后台发起，2=用户发起）")
    private Integer refundType;

    @Schema(description = "退款原因")
    private String refundReason;
}
