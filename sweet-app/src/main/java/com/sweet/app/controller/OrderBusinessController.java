package com.sweet.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.app.service.OrderBusinessService;
import com.sweet.app.vo.OrderDetailVo;
import com.sweet.app.vo.OrderPageVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单业务接口")
@RestController
@RequestMapping("/order-business")
@RequiredArgsConstructor
public class OrderBusinessController {

    private final OrderBusinessService orderBusinessService;

    @Operation(summary = "获取订单详情", description = "根据订单号获取订单详细信息（包含订单主表和明细列表）", parameters = {
            @Parameter(name = "orderNo", description = "订单号", required = true),
            @Parameter(name = "userId", description = "用户 ID", required = false)
    })
    @GetMapping("/detail")
    public ResponseEntity<OrderDetailVo> getOrderDetail(@RequestParam String orderNo,
                                                        @RequestParam(required = false) Integer userId) {
        return ResponseEntity.success(orderBusinessService.getOrderDetail(orderNo, userId));
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
    public ResponseEntity<Page<OrderPageVo>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer storeId,
            @RequestParam(required = false) Integer orderStatus,
            @RequestParam(required = false) Integer payStatus) {
        return ResponseEntity.success(orderBusinessService.getPage(pageNo, pageSize, orderNo, userId, storeId, orderStatus, payStatus));
    }
}