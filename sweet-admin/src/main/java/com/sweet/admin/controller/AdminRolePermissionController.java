package com.sweet.admin.controller;

import com.sweet.admin.service.AdminRolePermissionService;
import com.sweet.admin.vo.AdminPermIdNameOptionVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "角色权限关系管理")
@RequestMapping("/role-permission")
@RestController
@AllArgsConstructor
public class AdminRolePermissionController {

    private final AdminRolePermissionService adminRolePermissionService;

    @Operation(parameters = {
            @Parameter(name = "roleId", description = "角色ID", required = true)
    }, summary = "获取角色权限ID列表", description = "获取角色权限ID列表")
    @GetMapping("/{roleId}/permission-ids")
    public ResponseEntity<List<Integer>> getPermissionIdsByRoleId(@PathVariable Integer roleId) {
        return ResponseEntity.success(adminRolePermissionService.getPermissionIdsByRoleId(roleId));
    }

    @Operation(parameters = {
            @Parameter(name = "roleId", description = "角色ID", required = true)
    }, summary = "获取角色权限选项列表", description = "返回角色关联的权限ID和权限名称")
    @GetMapping("/{roleId}/permission-options")
    public ResponseEntity<List<AdminPermIdNameOptionVo>> getPermissionOptionsByRoleId(@PathVariable Integer roleId) {
        return ResponseEntity.success(adminRolePermissionService.getPermissionOptionsByRoleId(roleId));
    }

    @Operation(parameters = {
            @Parameter(name = "roleId", description = "角色ID", required = true)
    }, summary = "设置角色权限", description = "设置角色权限（覆盖式）")
    @PutMapping("/{roleId}/permissions")
    public ResponseEntity<Boolean> setPermissionsByRoleId(@PathVariable Integer roleId, @RequestBody List<String> permCodes){
        return ResponseEntity.success(adminRolePermissionService.setPermissionIdsByRoleId(roleId, permCodes));
    }

}
