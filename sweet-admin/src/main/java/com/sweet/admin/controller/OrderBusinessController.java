package com.sweet.admin.controller;

import com.sweet.admin.service.OrderBusinessService;
import com.sweet.admin.vo.OrderDetailVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单业务管理")
@RestController
@RequestMapping("/order-business")
@AllArgsConstructor
public class OrderBusinessController {

    private final OrderBusinessService orderBusinessService;

    @GetMapping("/{orderNo}")
    @Operation(parameters = {
            @Parameter(name = "orderNo", description = "订单号")
    }, summary = "获取订单详情", description = "获取订单详情（包含订单主表和明细列表）")
    public ResponseEntity<OrderDetailVo> getDetail(@PathVariable String orderNo) {
        return ResponseEntity.success(orderBusinessService.getDetailVoByOrderNo(orderNo));
    }

    @Operation(summary = "删除订单", description = "删除订单（逻辑删除）",
            parameters = {@Parameter(name = "orderNo", description = "订单号")}
    )
    @DeleteMapping("/{orderNo}")
    public ResponseEntity deleteOrder(@PathVariable String orderNo) {
        return ResponseEntity.success(orderBusinessService.deleteOrderByOrderNo(orderNo));
    }
}
