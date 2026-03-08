package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.AttributeValue;
import com.sweet.service.service.AttributeValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "商品属性值管理")
@RestController
@RequestMapping("/attribute-value")
@AllArgsConstructor
public class AttributeValueController {

    private final AttributeValueService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "valueName", description = "属性值名称")
    }, summary = "分页查询商品属性值", description = "分页查询商品属性值")
    public ResponseEntity<Page<AttributeValue>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) String valueName) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, valueName));

    }
}
