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

        return ResponseEntity.success(service.getPageByCondition(pageNo, pageSize, orderNo, userId, storeId, orderStatus, payStatus, startTime, endTime));

    }

    @GetMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "订单 id")
    }, summary = "获取订单详情", description = "获取订单详情（包含订单主表和明细列表）")
    public ResponseEntity<OrderDetailVo> getDetail(@PathVariable Integer id) {
        OrderMain orderMain = service.getOrderDetailById(id);
        if (orderMain == null) {
            return ResponseEntity.fail("订单不存在");
        }
        List<OrderDetail> orderDetails = service.getOrderDetailsByOrderNo(orderMain.getOrderNo());
        OrderDetailVo orderDetailVo = new OrderDetailVo()
                .setOrderMain(orderMain)
                .setOrderDetails(orderDetails);
        return ResponseEntity.success(orderDetailVo);
    }

    @PostMapping
    @Operation(summary = "新增订单", description = "新增订单")
    public ResponseEntity<Boolean> save(@RequestBody OrderMain entity) {
        return ResponseEntity.success(service.save(entity));
    }

    @PutMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "订单 id")
    }, summary = "更新订单", description = "更新订单")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody OrderMain entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "订单 id")
    }, summary = "删除订单", description = "根据订单 id 删除订单")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(service.removeById(id));
    }

}
