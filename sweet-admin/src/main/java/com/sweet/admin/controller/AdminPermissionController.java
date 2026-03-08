package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminPermission;
import com.sweet.admin.service.AdminPermissionService;
import com.sweet.admin.vo.AdminPermIdNameOptionVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Tag(name = "后台权限管理")
@RequestMapping("/permission")
@RestController
@AllArgsConstructor
public class AdminPermissionController {

    private final AdminPermissionService adminPermissionService;

    @Operation(parameters =  {
            @Parameter(name = "pageNo", description = "页码"),
            @Parameter(name = "pageSize", description = "每页数量"),
            @Parameter(name = "permName", description = "权限名称")
    },summary = "权限列表", description = "权限列表")
    @GetMapping
    public ResponseEntity<Page<AdminPermission>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                         @RequestParam(required = false) String permName) {
        return ResponseEntity.success(adminPermissionService.getPage(pageNo, pageSize, permName));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "权限ID", required = true)
    }, summary = "获取权限详情", description = "获取权限详情")
    @GetMapping("/{id}")
    public ResponseEntity<AdminPermission> getAdminPermission(@PathVariable Integer id) {
        return ResponseEntity.success(adminPermissionService.getById(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "权限ID", required = true)
    }, summary = "删除权限", description = "删除权限")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(adminPermissionService.removeById(id));
    }

    @Operation(summary = "添加权限", description = "添加权限")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody AdminPermission adminPermission) {
        return ResponseEntity.success(adminPermissionService.save(adminPermission));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "权限ID", required = true)
    }, summary = "修改权限", description = "修改权限")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody AdminPermission adminPermission) {
        adminPermission.setId(id);
        return ResponseEntity.success(adminPermissionService.updateById(adminPermission));
    }

    @Operation(summary = "perms下拉列表", description = "perms下拉列表")
    @GetMapping("/codes")
    public ResponseEntity<List<String>> getPermCodes(){
        return ResponseEntity.success(adminPermissionService.getPermCodes());
    }

    @Operation(summary = "权限管理父级权限下拉菜单", description = "权限管理父级权限下拉菜单")
    @GetMapping("/id-name-options")
    public ResponseEntity<List<AdminPermIdNameOptionVo>> getIdNameOptions(){
        return ResponseEntity.success(adminPermissionService.getIdNameOptions());
    }


}
