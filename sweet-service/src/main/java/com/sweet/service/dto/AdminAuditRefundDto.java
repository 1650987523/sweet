package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理员审核退款请求 DTO
 */
@Data
@Schema(description = "管理员审核退款请求")
public class AdminAuditRefundDto {

    @Schema(description = "退款单号")
    private String refundNo;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "审核状态（1=审核通过，2=审核拒绝）")
    private Integer auditStatus;

    @Schema(description = "审核原因/备注")
    private String auditReason;

    @Schema(description = "退款金额")
    private Long refundAmount;
}
