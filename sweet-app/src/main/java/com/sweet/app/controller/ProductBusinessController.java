package com.sweet.app.controller;


import com.sweet.app.service.ProductBusinessService;
import com.sweet.app.dto.OrderCreateReqDto;
import com.sweet.app.vo.OrderCreateVo;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.vo.ProductSkuQueryVo;
import com.sweet.service.entity.ProductSku;
import com.sweet.service.vo.ProductWithCategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-business")
@Tag(name = "商品业务接口")
@RequiredArgsConstructor
public class ProductBusinessController {

    private final ProductBusinessService productBusinessService;

    @Operation(summary = "获取商品分类和分类对应的商品列表", description = "获取商品分类和分类对应的商品列表",
    parameters = {@Parameter(name = "storeId", description = "店铺 ID", required = true),
                  @Parameter(name = "productName", description = "商品名称", required = false)})
    @GetMapping("/product-with-categories/{storeId}")
    public ResponseEntity<List<ProductWithCategoryVo>> getProductsWithCategories(@PathVariable Long storeId, @RequestParam(required = false) String productName) {
        return ResponseEntity.success(productBusinessService.getProductsWithCategories(storeId, productName));
    }

    @Operation(summary = "获取商品信息和商品属性信息", description = "获取商品信息和商品属性信息",
               parameters = {@Parameter(name = "productId", description = "商品 ID", required = true)})
    @GetMapping("/attribute-info/{productId}")
    public ResponseEntity getProductWithAttributeInfo(@PathVariable Long productId) {
        return ResponseEntity.success(productBusinessService.getProductWithAttributeInfo(productId));
    }

    @Operation(summary = "根据选中的规格查询 SKU 信息", description = "前端选择完规格后调用此接口获取 SKU 信息")
    @PostMapping("/sku-by-attrs")
    public ResponseEntity<ProductSku> getSkuInfoByAttrs(@RequestBody ProductSkuQueryVo productSkuQueryVo) {
        return ResponseEntity.success(productBusinessService.getSkuInfoByAttrs(productSkuQueryVo));
    }

    @Operation(summary = "创建订单（扫码点餐）", description = "扫码点餐创建订单接口")
    @PostMapping("/order/create")
    public ResponseEntity<OrderCreateVo> createOrder(@RequestBody OrderCreateReqDto req) {
        return ResponseEntity.success(productBusinessService.createOrder(req));
    }

    @Operation(summary = "取消订单", description = "取消待支付订单，自动恢复库存",
            parameters = {
                @Parameter(name = "orderNo", description = "订单编号", required = true),
                @Parameter(name = "userId", description = "用户 ID", required = false),
                @Parameter(name = "cancelReason", description = "取消原因", required = false)
    })
    @PostMapping("/order/cancel")
    public ResponseEntity<Boolean> cancelOrder(@RequestParam String orderNo,
                                               @RequestParam(required = false) Long userId,
                                               @RequestParam(required = false) String cancelReason) {
        return ResponseEntity.success(productBusinessService.cancelOrder(orderNo, userId, cancelReason));
    }
}
