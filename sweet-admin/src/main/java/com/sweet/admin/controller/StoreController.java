package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.Store;
import com.sweet.service.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "门店管理")
@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

    private final StoreService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "name", description = "门店名称"),
            @Parameter(name = "storeId", description = "门店Id"),
    }, summary = "分页查询门店", description = "分页查询门店")
    public ResponseEntity<Page<Store>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) Integer storeId,
                                              @RequestParam(required = false) String name) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, storeId, name));

    }

    @GetMapping("/list")
    @Operation(summary = "获取门店列表", description = "获取门店列表（用于下拉选项，支持传入 storeId 筛选）")
    public ResponseEntity<java.util.List<Store>> listAll(@RequestParam(required = false) Integer storeId) {
        return ResponseEntity.success(service.getStoresById(storeId));
    }

    @PostMapping
    @Operation(summary = "新增门店", description = "新增门店")
    public ResponseEntity<Boolean> save(@RequestBody Store entity) {
        return ResponseEntity.success(service.save(entity));
    }

    @PutMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "门店id")
    }, summary = "更新门店", description = "更新门店")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody Store entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "门店id")
    }, summary = "删除门店", description = "根据门店id删除门店")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(service.removeById(id));
    }

}
