package com.sweet.app.controller;

import com.sweet.service.dto.RefundReqVo;
import com.sweet.service.dto.RefundResVo;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.service.OrderRefundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单退款接口")
@RestController
@RequestMapping("/order/refund")
@RequiredArgsConstructor
public class OrderRefundController {

    private final OrderRefundService orderRefundService;

    @Operation(summary = "申请退款", description = "用户申请退款或商家操作退款")
    @PostMapping("/apply")
    public ResponseEntity<RefundResVo> applyRefund(@RequestBody RefundReqVo refundReqVo) {
        return ResponseEntity.success(orderRefundService.applyRefund(refundReqVo));
    }
}
