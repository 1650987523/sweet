package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 退款申请请求 DTO
 */
@Data
@Schema(description = "退款申请请求 DTO")
public class RefundReqVo {

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "退款原因")
    private String reason;
}
