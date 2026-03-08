package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.service.AdminUserService;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.ProductSku;
import com.sweet.service.service.ProductSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "商品SKU管理")
@RestController
@RequestMapping("/product-sku")
@AllArgsConstructor
public class ProductSkuController {

    private final ProductSkuService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "productId", description = "商品ID")
    }, summary = "分页查询商品SKU", description = "分页查询商品SKU")
    public ResponseEntity<Page<ProductSku>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) Long productId) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, productId));

    }

    @PostMapping
    @Operation(summary = "新增商品SKU", description = "新增商品SKU")
    public ResponseEntity<Boolean> save(@RequestBody ProductSku entity) {
        return ResponseEntity.success(service.save(entity));
    }

    @PutMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "SKU id")
    }, summary = "更新商品SKU", description = "更新商品SKU")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody ProductSku entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "SKU id")
    }, summary = "删除商品SKU", description = "根据SKU id删除商品SKU")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.success(service.removeById(id));
    }

}

