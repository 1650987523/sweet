package com.sweet.app.controller;

import com.sweet.app.service.PayService;
import com.sweet.app.vo.PayCallbackVo;
import com.sweet.app.vo.PayOrderVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "支付接口")
@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    @Operation(summary = "获取微信支付参数", description = "用户支付时获取微信所需的支付参数",
            parameters = {
                    @Parameter(description = "订单号", required = true),
                    @Parameter(description = "用户 ID", required = false)
            })
    @GetMapping("/params")
    public ResponseEntity<PayOrderVo> getPayParams(@RequestParam String orderNo,
                                                   @RequestParam(required = false) Integer userId) {
        return ResponseEntity.success(payService.getPayParams(orderNo, userId));
    }

    @Operation(summary = "支付回调通知", description = "接收微信支付/支付宝的异步回调通知")
    @PostMapping("/callback")
    public ResponseEntity<String> payCallback(@RequestBody PayCallbackVo vo) {
        return ResponseEntity.success(payService.handlePayCallback(vo));
    }
}
