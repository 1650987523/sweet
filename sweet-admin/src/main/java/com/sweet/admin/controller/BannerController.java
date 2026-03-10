package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.dto.BannerBatchSaveDto;
import com.sweet.service.dto.BannerUpdateStatusDto;
import com.sweet.service.entity.Banner;
import com.sweet.service.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Tag(name = "轮播图管理")
@RestController
@RequestMapping("/banner")
@AllArgsConstructor
public class BannerController {

    private final BannerService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "status", description = "状态 0-下架 1-上架"),
            @Parameter(name = "linkType", description = "跳转类型：1-商品 2-分类 3-活动 4-外链 5-页面"),
            @Parameter(name = "title", description = "轮播图标题"),
            @Parameter(name = "pageNo", description = "当前页码"),
            @Parameter(name = "pageSize", description = "每页条数")
    }, summary = "分页查询轮播图", description = "分页查询轮播图")
    public ResponseEntity<Page<Banner>> getPage(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer linkType,
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, storeId, status, linkType, title));
    }

    @PostMapping
    @Operation(summary = "新增轮播图", description = "新增轮播图")
    public ResponseEntity<Boolean> save(@RequestBody Banner banner) {
        return ResponseEntity.success(service.save(banner));
    }

    @PostMapping("/batch")
    @Operation(summary = "批量新增轮播图", description = "批量新增轮播图")
    public ResponseEntity<Boolean> saveBatch(@RequestBody BannerBatchSaveDto dto) {
        return ResponseEntity.success(service.saveBatch(dto));
    }

    @PutMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "轮播图 id")
    }, summary = "更新轮播图", description = "更新轮播图")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody Banner banner) {
        banner.setId(id);
        return ResponseEntity.success(service.updateById(banner));
    }

    @PutMapping("/status")
    @Operation(summary = "更新轮播图状态", description = "更新轮播图状态")
    public ResponseEntity<Boolean> updateStatus(@RequestBody BannerUpdateStatusDto dto) {
        return ResponseEntity.success(service.updateStatus(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters = {@Parameter(name = "id", description = "轮播图 id")}, summary = "删除轮播图", description = "根据轮播图 id 删除")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        service.removeById(id);
        return ResponseEntity.success(true);
    }

    @GetMapping("/{id}")
    @Operation(parameters = {@Parameter(name = "id", description = "轮播图 id")}, summary = "获取轮播图详情", description = "根据轮播图 id 获取详情")
    public ResponseEntity<Banner> getById(@PathVariable Long id) {
        return ResponseEntity.success(service.getById(id));
    }

    @Operation(summary = "轮播图图片上传", description = "轮播图图片上传")
    @PostMapping("/images/upload")
    public ResponseEntity<List<String>> uploadImages(@RequestParam Long storeId, @RequestPart List<MultipartFile> files) {
        return ResponseEntity.success(service.uploadBannerImages(storeId, files));
    }

    @Operation(summary = "轮播图图片删除", description = "轮播图图片删除")
    @DeleteMapping("/images")
    public ResponseEntity<Boolean> deleteImages(@RequestParam List<String> images) {
        return ResponseEntity.success(service.deleteBannerImages(images));
    }
}
