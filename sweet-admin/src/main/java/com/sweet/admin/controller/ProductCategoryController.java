package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.dto.ProductCategoryDto;
import com.sweet.service.entity.ProductCategory;
import com.sweet.service.service.ProductCategoryService;
import com.sweet.service.vo.ProductCategoryNodeVo;
import com.sweet.service.vo.ProductCategoryOptionVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品分类管理")
@RestController
@RequestMapping("/product-category")
@AllArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "categoryName", description = "分类名称"),
            @Parameter(name = "status", description = "状态 1-启用 2-禁用"),
            @Parameter(name = "current", description = "当前页码"),
            @Parameter(name = "size", description = "每页条数")
    }, summary = "分页查询商品分类", description = "分页查询商品分类")
    public ResponseEntity<Page<ProductCategory>> getPage(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "100") Integer size) {

        return ResponseEntity.success(service.getPage(current, size, storeId, categoryName, status));
    }

    @PostMapping
    @Operation(summary = "新增商品分类", description = "新增商品分类")
    public ResponseEntity<Boolean> save(@ModelAttribute ProductCategoryDto dto) {
        return ResponseEntity.success(service.save(dto));
    }

    @PutMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "分类 id")
    }, summary = "更新商品分类", description = "更新商品分类")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @ModelAttribute ProductCategoryDto dto) {
        dto.setId(id);
        return ResponseEntity.success(service.updateById(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "分类 id")
    }, summary = "删除商品分类", description = "根据分类 id 删除商品分类")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        service.removeCategory(id);
        return ResponseEntity.success(true);
    }

    @GetMapping("/tree")
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID")
    }, summary = "获取商品分类树", description = "获取商品分类树形结构")
    public ResponseEntity<List<ProductCategoryNodeVo>> getCategoryTree(
            @RequestParam(required = false) Long storeId) {
        return ResponseEntity.success(service.getCategoryTree(storeId));
    }

    @GetMapping("/options")
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID")
    }, summary = "获取商品分类选项", description = "获取商品分类选项")
    public ResponseEntity<List<ProductCategoryOptionVo>> getProductCategoryOptions(
            @RequestParam(required = false) Long storeId) {
        return ResponseEntity.success(service.getProductCategoryOptions(storeId));
    }
}
