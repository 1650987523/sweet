package com.sweet.app.controller;

import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.service.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "订单明细接口")
@RestController
@RequestMapping("/order-detail")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Operation(summary = "订单明细列表", description = "根据订单编号获取订单明细列表", parameters = {
            @Parameter(name = "orderNo", description = "订单编号")
    })
    @GetMapping("/{orderNo}")
    public ResponseEntity<List<OrderDetail>> getOrderDetails(@PathVariable String orderNo) {
        return ResponseEntity.success(orderDetailService.getOrderDetailsByOrderNo(orderNo));
    }
}
