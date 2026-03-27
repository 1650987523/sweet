package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.AdminAuditRefundDto;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.dto.ApplyRefundDto;
import com.sweet.service.dto.ApplyRefundVo;
import com.sweet.service.entity.OrderRefund;
import com.sweet.service.service.OrderRefundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "退款管理")
@RestController
@RequestMapping("/order-refund")
@AllArgsConstructor
public class OrderRefundController {

    private final OrderRefundService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "refundNo", description = "退款单号（模糊查询）"),
            @Parameter(name = "orderNo", description = "订单号（模糊查询）"),
            @Parameter(name = "userId", description = "用户 ID"),
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "refundStatus", description = "退款状态"),
            @Parameter(name = "startTime", description = "开始时间（创建时间）"),
            @Parameter(name = "endTime", description = "结束时间（创建时间）")
    }, summary = "分页查询退款", description = "分页查询退款（支持多条件）")
    public ResponseEntity<Page<OrderRefund>> getPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String refundNo,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer storeId,
            @RequestParam(required = false) Integer refundStatus,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {

        return ResponseEntity.success(service.page(pageNo, pageSize, refundNo, orderNo, userId, storeId, refundStatus, startTime, endTime));
    }

    @GetMapping("/{id}")
    @Operation(parameters = {
            @Parameter(name = "id", description = "退款 id")
    }, summary = "获取退款详情", description = "获取退款详情")
    public ResponseEntity<OrderRefund> getDetail(@PathVariable Long id) {
        return ResponseEntity.success(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "新增退款", description = "新增退款")
    public ResponseEntity<Boolean> save(@RequestBody OrderRefund entity) {
        return ResponseEntity.success(service.save(entity));
    }

    @PutMapping("/{id}")
    @Operation(parameters = {
            @Parameter(name = "id", description = "退款 id")
    }, summary = "更新退款", description = "更新退款")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody OrderRefund entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @PostMapping("/apply")
    @Operation(summary = "申请退款", description = "申请退款")
    public ResponseEntity<ApplyRefundVo> adminApplyRefund(@RequestBody ApplyRefundDto dto) {
        return ResponseEntity.success(service.adminApplyRefund(dto));
    }

    @PutMapping("/audit")
    @Operation(summary = "门店审核退款", description = "门店审核退款")
    public ResponseEntity<Boolean> adminAuditRefund(@RequestBody AdminAuditRefundDto dto) {
        return ResponseEntity.success(service.adminAuditRefund(dto));
    }

}
