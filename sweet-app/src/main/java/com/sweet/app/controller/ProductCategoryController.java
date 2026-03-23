package com.sweet.app.controller;


import com.sweet.common.response.ResponseEntity;
import com.sweet.service.service.ProductCategoryService;
import com.sweet.service.vo.ProductCategoryNodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-category")
@Tag(name = "商品分类接口")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

}
