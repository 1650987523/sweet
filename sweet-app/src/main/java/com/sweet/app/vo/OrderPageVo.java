package com.sweet.app.vo;

import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单分页查询结果
 */
@Data
@Accessors(chain = true)
public class OrderPageVo {

    @Schema(description = "订单主表信息")
    private OrderMain orderMain;

    @Schema(description = "订单明细列表")
    private List<OrderDetail> orderDetails;
}
