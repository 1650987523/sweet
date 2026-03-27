package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 退款响应 DTO
 */
@Data
@Accessors(chain = true)
public class ApplyRefundVo {

    @Schema(description = "退款单号")
    private String refundNo;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "退款金额（单位：分）")
    private Long refundAmount;

    @Schema(description = "退款状态（0=退款中，1=退款成功，2=退款失败）")
    private Integer refundStatus;

    @Schema(description = "退款时间")
    private String refundTime;
}
