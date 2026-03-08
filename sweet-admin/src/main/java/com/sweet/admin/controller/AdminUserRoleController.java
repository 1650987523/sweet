package com.sweet.admin.controller;

import com.sweet.admin.service.AdminUserRoleService;
import com.sweet.admin.vo.AdminRoleOptionVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户角色关系管理")
@RequestMapping("/user-role")
@RestController
@AllArgsConstructor
public class AdminUserRoleController {

    private final AdminUserRoleService adminUserRoleService;

    @Operation(parameters = {
            @Parameter(name = "userId", description = "用户ID", required = true)
    }, summary = "获取用户角色ID列表", description = "获取用户角色ID列表")
    @GetMapping("/{userId}/role-ids")
    public ResponseEntity<List<Integer>> getRoleIdsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.success(adminUserRoleService.getRoleIdsByUserId(userId));
    }

    @Operation(parameters = {
            @Parameter(name = "userId", description = "用户ID", required = true)
    }, summary = "获取用户角色选项列表", description = "返回用户关联的角色ID和角色名称")
    @GetMapping("/{userId}/role-options")
    public ResponseEntity<List<AdminRoleOptionVo>> getRoleOptionsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.success(adminUserRoleService.getRoleOptionsByUserId(userId));
    }

    @Operation(parameters = {
            @Parameter(name = "userId", description = "用户ID", required = true)
    }, summary = "设置用户角色", description = "设置用户角色（覆盖式）")
    @PutMapping("/{userId}/roles")
    public ResponseEntity<Boolean> setRoleIdsByUserId(@PathVariable Integer userId, @RequestBody List<Integer> roleIds) {
        return ResponseEntity.success(adminUserRoleService.setRoleIdsByUserId(userId, roleIds));
    }

}
