package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.vo.OrderDetailVo;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.service.OrderMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "订单管理")
@RestController
@RequestMapping("/order-main")
@AllArgsConstructor
public class OrderMainController {

    private final OrderMainService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "orderNo", description = "订单号（模糊查询）"),
            @Parameter(name = "userId", description = "用户 ID"),
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "orderStatus", description = "订单状态"),
            @Parameter(name = "payStatus", description = "支付状态"),
            @Parameter(name = "startTime", description = "开始时间（创建时间）"),
            @Parameter(name = "endTime", description = "结束时间（创建时间）")
    }, summary = "分页查询订单", description = "分页查询订单（支持多条件）")
    public ResponseEntity<Page<OrderMain>> getPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer storeId,
            @RequestParam(required = false) Integer orderStatus,
            @RequestParam(required = false) Integer payStatus,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        return ResponseEntity.success(service.adminPage(pageNo, pageSize, orderNo, userId, storeId, orderStatus, payStatus, startTime, endTime));

    }



    @PutMapping("/{orderNo}/finish")
    @Operation(summary = "完成订单", description = "完成订单",parameters = {
            @Parameter(name = "orderNo", description = "订单号")
    })
    public ResponseEntity<Boolean> adminFinishRefund(@PathVariable String orderNo) {
        return ResponseEntity.success(service.adminFinishOrder(orderNo));
    }

}
