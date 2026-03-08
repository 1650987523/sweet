package com.sweet.admin.controller;

import com.sweet.admin.service.ProductBusinessService;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.dto.AttributeDTO;
import com.sweet.service.dto.ProductDTO;
import com.sweet.service.dto.ProductUploadDto;
import com.sweet.service.entity.AttributeValue;
import com.sweet.service.service.AttributeService;
import com.sweet.service.service.AttributeValueService;
import com.sweet.service.vo.AttributeWithValueVO;
import com.sweet.service.vo.ProductDetailWithRelationsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品业务管理")
@RestController
@RequestMapping("/product-business")
@AllArgsConstructor
public class ProductBusinessController {

    private final AttributeService attributeService;
    private final AttributeValueService attributeValueService;
    private final ProductBusinessService productBusinessService;

    @Operation(summary = "新增属性模板", description = "新增属性模板（包含属性值）")
    @PostMapping("/attribute")
    public ResponseEntity<Long> saveAttribute(@RequestBody AttributeDTO dto) {
        return ResponseEntity.success(attributeService.saveAttribute(dto));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "属性 ID")
    }, summary = "更新属性模板", description = "更新属性模板（包含属性值）")
    @PutMapping("/attribute/{id}")
    public ResponseEntity<Boolean> updateAttribute(@PathVariable Long id, @RequestBody AttributeDTO dto) {
        dto.setId(id);
        return ResponseEntity.success(attributeService.updateAttribute(dto));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "属性 ID")
    }, summary = "删除属性模板", description = "删除属性模板（同时删除关联的属性值）")
    @DeleteMapping("/attribute/{id}")
    public ResponseEntity<Boolean> deleteAttribute(@PathVariable Long id) {
        attributeService.removeAttribute(id);
        return ResponseEntity.success(true);
    }

    @Operation(parameters = {
            @Parameter(name = "attributeId", description = "属性 ID")
    }, summary = "根据属性 ID 获取属性值列表", description = "根据属性 ID 获取属性值列表")
    @GetMapping("/attribute/{attributeId}/values")
    public ResponseEntity<List<AttributeValue>> getAttributeValues(@PathVariable Long attributeId) {
        return ResponseEntity.success(attributeValueService.getByAttributeId(attributeId));
    }

    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID")
    }, summary = "获取属性模板列表（含属性值）", description = "根据门店 ID 获取属性模板列表及对应的属性值列表")
    @GetMapping("/attribute-templates")
    public ResponseEntity<List<AttributeWithValueVO>> getAttributeTemplates(@RequestParam Long storeId) {
        return ResponseEntity.success(attributeService.getAttributeTemplates(storeId));
    }

    @Operation(summary = "创建商品", description = "创建商品（包含 SKU、属性关联）")
    @PostMapping("/product")
    public ResponseEntity<Long> createProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity.success(productBusinessService.createProduct(dto));
    }

    @Operation(summary = "更新商品", description = "更新商品（全量替换 SKU、属性关联）")
    @PutMapping("/product/{id}")
    public ResponseEntity<Boolean> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        dto.setId(id);
        return ResponseEntity.success(productBusinessService.updateProduct(dto));
    }

    @Operation(summary = "商品图片上传", description = "商品图片上传")
    @PostMapping("/product/images")
    public ResponseEntity<List<String>> uploadProductImages(@ModelAttribute ProductUploadDto dto) {
        return ResponseEntity.success(productBusinessService.uploadProductImages(dto));
    }

    @Operation(summary = "商品图片删除", description = "商品图片删除")
    @DeleteMapping("/product/images")
    public ResponseEntity<Boolean> deleteProductImages(@RequestParam List<String> images) {
        return ResponseEntity.success(productBusinessService.deleteProductImages(images));
    }

    @Operation(parameters = {
            @Parameter(name = "productId", description = "商品 ID")
    }, summary = "获取商品详情", description = "根据商品 ID 获取商品详情（包含属性关联、SKU 及 SKU 属性关联）")
    @GetMapping("/product/{productId}/detail")
    public ResponseEntity<ProductDetailWithRelationsVO> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.success(productBusinessService.getProductDetailById(productId));
    }

    @Operation(parameters = {
            @Parameter(name = "productId", description = "商品 ID")
    }, summary = "删除商品", description = "根据商品 ID 删除商品（级联删除属性关联、SKU 及 SKU 属性关联）")
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.success(productBusinessService.deleteProductById(productId));
    }

}