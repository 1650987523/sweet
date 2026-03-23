package com.sweet.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "订单创建响应 VO")
public class OrderCreateVo {

    @Schema(description = "订单 ID")
    private Integer orderId;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "订单总金额（单位：分）")
    private Long totalAmount;

    @Schema(description = "实付金额（单位：分）")
    private Long payAmount;

    @Schema(description = "订单状态")
    private Integer orderStatus;
}
