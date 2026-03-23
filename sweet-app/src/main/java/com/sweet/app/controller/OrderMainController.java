package com.sweet.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.service.OrderMainService;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.OrderMain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "订单主表接口")
@RestController
@RequestMapping("/order-main")
@RequiredArgsConstructor
public class OrderMainController {

    private final OrderMainService orderMainService;

    @Operation(summary = "订单详情", description = "根据订单编号获取 order_main 表中的信息")
    @GetMapping("/{orderNo}")
    public ResponseEntity<OrderMain> getInfoByOrderNo(@PathVariable String orderNo) {
        return ResponseEntity.success(orderMainService.getInfo(orderNo, null));
    }

    @Operation(summary = "订单分页列表", description = "订单分页列表（支持多条件查询）", parameters = {
            @Parameter(name = "pageNo", description = "页码"),
            @Parameter(name = "pageSize", description = "每页数量"),
            @Parameter(name = "orderNo", description = "订单号（模糊查询）"),
            @Parameter(name = "userId", description = "用户 ID"),
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "orderStatus", description = "订单状态"),
            @Parameter(name = "payStatus", description = "支付状态")
    })
    @GetMapping("/page")
    public ResponseEntity<Page<OrderMain>> page(
            @RequestParam Integer pageNo,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer storeId,
            @RequestParam(required = false) Integer orderStatus,
            @RequestParam(required = false) Integer payStatus,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return ResponseEntity.success(orderMainService.page(pageNo, pageSize, orderNo, userId, storeId, orderStatus, payStatus, startTime, endTime));
    }
}
