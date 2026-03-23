package com.sweet.app.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.Store;
import com.sweet.service.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "轮播图接口")
@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "获取所有门店", description = "获取所有门店")
    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.success(storeService.getAllStores());
    }

    @Operation(summary = "获取门店分页数据", description = "获取门店分页数据", parameters = {
            @Parameter(name = "pageNo", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true),
            @Parameter(name = "storeName", description = "门店名称", required = false)
    })
    @GetMapping("/page")
    public ResponseEntity<Page<Store>> getPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "") String storeName) {
        return ResponseEntity.success(storeService.getAppPage(pageNo, pageSize, storeName));
    }

    @Operation(summary = "获取门店详情", description = "获取门店详情", parameters = {
            @Parameter(name = "storeId", description = "门店ID", required = true)
    })
    @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long storeId) {
        return ResponseEntity.success(storeService.getById(storeId));
    }
}
