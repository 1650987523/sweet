package com.sweet.admin.vo;

import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "订单详情 VO（包含订单主表和明细列表）")
public class OrderDetailVo {

    @Schema(description = "订单主表信息")
    private OrderMain orderMain;

    @Schema(description = "订单明细列表")
    private List<OrderDetail> orderDetails;
}
