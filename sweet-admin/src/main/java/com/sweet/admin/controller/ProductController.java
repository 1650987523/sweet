package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.dto.ProductStatusDto;
import com.sweet.service.entity.Product;
import com.sweet.service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "商品管理")
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "categoryId", description = "分类 ID"),
            @Parameter(name = "productName", description = "商品名称"),
            @Parameter(name = "status", description = "商品状态 0=禁用 1=正常 2=下架"),
            @Parameter(name = "isHot", description = "是否热卖"),
            @Parameter(name = "isRecommend", description = "是否推荐"),
            @Parameter(name = "isNew", description = "是否新品"),
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条")
    }, summary = "分页查询商品", description = "分页查询商品")
    public ResponseEntity<Page<Product>> getPage(@RequestParam(required = false) Long storeId,
                                              @RequestParam(required = false) Long categoryId,
                                              @RequestParam(required = false) String productName,
                                              @RequestParam(required = false) Integer status,
                                              @RequestParam(required = false) Boolean isHot,
                                              @RequestParam(required = false) Boolean isRecommend,
                                              @RequestParam(required = false) Boolean isNew,
                                              @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, storeId, categoryId, productName, status, isHot, isRecommend, isNew));

    }

    @PostMapping
    @Operation(summary = "新增商品", description = "新增商品")
    public ResponseEntity<Boolean> save(@RequestBody Product entity) {
        return ResponseEntity.success(service.save(entity));
    }

    @PutMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "商品id")
    }, summary = "更新商品", description = "更新商品")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody Product entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "商品id")
    }, summary = "删除商品", description = "根据商品id删除商品")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.success(service.removeById(id));
    }

    @Operation(summary = "商品状态修改", description = "商品状态修改")
    @PutMapping("/status")
    public ResponseEntity<Boolean> updateStatus(@RequestBody ProductStatusDto dto) {
        return ResponseEntity.success(service.updateStatus(dto));
    }

}