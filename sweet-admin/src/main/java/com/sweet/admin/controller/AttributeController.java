package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.Attribute;
import com.sweet.service.service.AttributeService;
import com.sweet.service.vo.AttributeOptionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性模板管理 Controller
 */
@Tag(name = "商品属性管理")
@RestController
@RequestMapping("/attribute")
@AllArgsConstructor
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "name", description = "属性名称"),
            @Parameter(name = "type", description = "属性类型 1=销售规格 2=商品参数"),
            @Parameter(name = "status", description = "状态 0=禁用 1=正常"),
            @Parameter(name = "pageNo", description = "当前页码"),
            @Parameter(name = "pageSize", description = "每页条数")
    }, summary = "分页查询属性", description = "分页查询属性")
    public ResponseEntity<Page<Attribute>> getPage(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return ResponseEntity.success(attributeService.getPage(pageNo, pageSize, storeId, name, type, status));
    }

    @GetMapping("/list")
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "attributeType", description = "属性类型 1=销售规格 2=商品参数")
    }, summary = "获取属性选项列表", description = "获取属性选项列表（用于下拉选择）")
    public ResponseEntity<List<AttributeOptionVO>> getOptionList(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer attributeType) {

        return ResponseEntity.success(attributeService.getOptionList(storeId, attributeType));
    }
}
