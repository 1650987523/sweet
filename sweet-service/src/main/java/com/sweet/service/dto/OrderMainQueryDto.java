package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Schema(description = "订单查询条件 DTO")
public class OrderMainQueryDto {

    @Schema(description = "订单号（模糊查询）")
    private String orderNo;

    @Schema(description = "用户 ID")
    private Integer userId;

    @Schema(description = "门店 ID")
    private Integer storeId;

    @Schema(description = "订单状态")
    private Integer orderStatus;

    @Schema(description = "支付状态")
    private Integer payStatus;

    @Schema(description = "开始时间（创建时间）")
    private LocalDateTime startTime;

    @Schema(description = "结束时间（创建时间）")
    private LocalDateTime endTime;
}
