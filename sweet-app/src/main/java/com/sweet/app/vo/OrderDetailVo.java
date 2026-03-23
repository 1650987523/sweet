package com.sweet.app.vo;

import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "订单详情响应 VO（包含订单主表和明细列表）")
public class OrderDetailVo {

    @Schema(description = "订单主表信息")
    private OrderMain orderMain;

    @Schema(description = "订单明细列表")
    private List<OrderDetail> orderDetails;
}
