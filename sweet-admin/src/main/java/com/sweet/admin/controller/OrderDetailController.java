package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.service.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单明细管理")
@RestController
@RequestMapping("/order-detail")
@AllArgsConstructor
public class OrderDetailController {

    private final OrderDetailService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "orderNo", description = "订单号")
    }, summary = "分页查询订单明细", description = "分页查询订单明细")
    public ResponseEntity<Page<OrderDetail>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) String orderNo) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, orderNo));

    }

    @PostMapping
    @Operation(summary = "新增订单明细", description = "新增订单明细")
    public ResponseEntity<Boolean> save(@RequestBody OrderDetail entity) {
        return ResponseEntity.success(service.save(entity));
    }

    @PutMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "订单明细 id")
    }, summary = "更新订单明细", description = "更新订单明细")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody OrderDetail entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "订单明细 id")
    }, summary = "删除订单明细", description = "根据订单明细 id 删除订单明细")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(service.removeById(id));
    }

}
