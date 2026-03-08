package com.sweet.admin.controller;

import com.sweet.admin.entity.AdminMenu;
import com.sweet.admin.service.*;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "多表聚合业务处理")
@RequestMapping("/aggregation")
@RestController
@AllArgsConstructor
public class AdminAggregationController {

    private final AdminAggregationService adminAggregationService;

    @Operation(parameters = {
            @Parameter(name = "roleId", description = "角色ID", required = true)
    }, summary = "获取角色对应的菜单权限列表", description = "获取角色对应的菜单权限列表")
    @GetMapping("/{roleId}/menus")
    public ResponseEntity<List<AdminMenu>> getMenusByRoleId(@PathVariable Integer roleId) {
        return ResponseEntity.success(adminAggregationService.getMenusByRoleId(roleId));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "权限ID", required = true)
    }, summary = "删除权限及其关联的角色关系", description = "删除权限时同步清理admin_role_permission中间表")
    @DeleteMapping("/permission/{id}")
    public ResponseEntity<Boolean> deletePermissionById(@PathVariable Integer id) {
        return ResponseEntity.success(adminAggregationService.deletePermissionWithRelations(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "用户ID", required = true)
    }, summary = "删除用户及其关联关系", description = "删除用户时同步清理admin_user_dept和admin_user_role中间表")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Integer id) {
        return ResponseEntity.success(adminAggregationService.deleteUserWithRelations(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "角色ID", required = true)
    }, summary = "删除角色及其关联关系", description = "删除角色时同步清理admin_user_role和admin_role_permission中间表")
    @DeleteMapping("/role/{id}")
    public ResponseEntity<Boolean> deleteRoleById(@PathVariable Integer id) {
        return ResponseEntity.success(adminAggregationService.deleteRoleWithRelations(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "部门ID", required = true)
    }, summary = "删除部门及其关联关系", description = "删除部门时同步清理admin_user_dept中间表")
    @DeleteMapping("/dept/{id}")
    public ResponseEntity<Boolean> deleteDeptById(@PathVariable Integer id) {
        return ResponseEntity.success(adminAggregationService.deleteDeptWithRelations(id));
    }

}
