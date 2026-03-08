package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.response.ResponseEntity;
import com.sweet.service.dto.TableQrcodeDto;
import com.sweet.service.entity.TableQrcode;
import com.sweet.service.service.TableQrcodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "桌码管理")
@RestController
@RequestMapping("/table-qrcode")
@AllArgsConstructor
public class TableQrCodeController {

    private final TableQrcodeService service;

    @GetMapping
    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "当前页"),
            @Parameter(name = "pageSize", description = "每页多少条"),
            @Parameter(name = "qrcodeName", description = "桌号名称")
    }, summary = "分页查询桌码", description = "分页查询桌码")
    public ResponseEntity<Page<TableQrcode>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) String qrcodeName) {

        return ResponseEntity.success(service.getPage(pageNo, pageSize, qrcodeName));

    }

    @PostMapping("/save")
    @Operation(summary = "新增桌码", description = "新增桌码")
    public ResponseEntity<Boolean> save(@RequestBody TableQrcodeDto dto) {
        return ResponseEntity.success(service.save(dto));
    }

    @PutMapping("update/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "桌码id")
    }, summary = "更新桌码", description = "更新桌码")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody TableQrcode entity) {
        entity.setId(id);
        return ResponseEntity.success(service.updateById(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(parameters =  {
            @Parameter(name = "id", description = "桌码id")
    }, summary = "删除桌码", description = "根据桌码id删除桌码")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(service.removeById(id));
    }

    @DeleteMapping("/del/qrcode")
    @Operation(parameters =  {
            @Parameter(name = "qrcodeUrl", description = "桌码url")
    }, summary = "删除桌码图片", description = "根据桌码key删除桌码")
    public ResponseEntity<Boolean> deleteQrcode(@RequestParam String qrcodeUrl) {
        return ResponseEntity.success(service.deleteQrcode(qrcodeUrl));
    }

}