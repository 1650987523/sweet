package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminDict;
import com.sweet.admin.service.AdminDictService;
import com.sweet.admin.vo.AdminDictVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典管理接口
 */
@Tag(name = "数据字典管理")
@RestController
@RequestMapping("/dict")
@AllArgsConstructor
public class AdminDictController {

    private final AdminDictService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "dictType", description = "字典类型"),
            @Parameter(name = "dictLabel", description = "字典标签（模糊查询）"),
            @Parameter(name = "status", description = "状态")
    }, summary = "分页查询字典", description = "分页查询数据字典")
    public ResponseEntity<Page<AdminDict>> getPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String dictType,
            @RequestParam(required = false) String dictLabel,
            @RequestParam(required = false) Integer status) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, dictType, dictLabel, status));
    }

    @GetMapping("/{id}")
    @Operation(parameters = {
            @Parameter(name = "id", description = "字典 ID")
    }, summary = "获取字典详情", description = "根据 ID 获取字典详情")
    public ResponseEntity<AdminDict> getDetail(@PathVariable Long id) {
        return ResponseEntity.success(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "新增字典", description = "新增数据字典")
    public ResponseEntity<Boolean> save(@RequestBody AdminDict entity) {
        return ResponseEntity.success(service.save(entity));
    }

    @PutMapping("/{id}")
    @Operation(parameters = {
            @Parameter(name = "id", description = "字典 ID")
    }, summary = "更新字典", description = "更新数据字典")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody AdminDict entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters = {
            @Parameter(name = "id", description = "字典 ID")
    }, summary = "删除字典", description = "根据 ID 删除字典")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.success(service.removeById(id));
    }

    @GetMapping("/types")
    @Operation(summary = "获取所有字典类型", description = "获取所有字典类型（去重）")
    public ResponseEntity<List<String>> getDictTypes() {
        return ResponseEntity.success(service.getDictTypes());
    }

    @GetMapping("/items/{dictType}")
    @Operation(parameters = {
            @Parameter(name = "dictType", description = "字典类型")
    }, summary = "获取字典项列表", description = "根据字典类型获取字典项列表")
    public ResponseEntity<List<AdminDictVo>> getDictItemsByType(@PathVariable String dictType) {
        return ResponseEntity.success(service.getDictItemsByType(dictType));
    }

    @GetMapping("/items")
    @Operation(parameters = {
            @Parameter(name = "dictTypes", description = "字典类型列表（逗号分隔）")
    }, summary = "批量获取字典项", description = "根据字典类型列表批量获取字典项")
    public ResponseEntity<Object> getDictItemsByTypes(
            @RequestParam(required = false) List<String> dictTypes) {
        return ResponseEntity.success(service.getDictItemsByTypes(dictTypes));
    }
}
