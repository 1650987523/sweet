package com.sweet.app.controller;

import com.sweet.common.response.ResponseEntity;
import com.sweet.service.entity.Banner;
import com.sweet.service.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "轮播图接口")
@RestController
@RequestMapping("/banner")
@AllArgsConstructor
public class BannerController {

    private final BannerService service;

    @GetMapping("/list")
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID")
    }, summary = "获取上架轮播图列表", description = "获取当前上架的轮播图列表,0代表不属于任何门店，即全店通用")
    public ResponseEntity<List<Banner>> getValidBanners(@RequestParam(required = false) Long storeId) {
        return ResponseEntity.success(service.getValidBanners(storeId));
    }
}
