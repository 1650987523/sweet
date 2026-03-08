package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminRole;
import com.sweet.admin.service.AdminRoleService;
import com.sweet.admin.vo.AdminRoleOptionVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台角色管理")
@RequestMapping("/role")
@RestController
@AllArgsConstructor
public class AdminRoleController {

    private final AdminRoleService adminRoleService;

    @Operation(parameters =  {
            @Parameter(name = "pageNo", description = "页码"),
            @Parameter(name = "pageSize", description = "每页数量"),
            @Parameter(name = "roleName", description = "角色名称"),
            @Parameter(name = "roleCode", description = "角色编码"),
            @Parameter(name = "status", description = "角色状态"),
    },summary = "角色列表", description = "角色列表")
    @GetMapping
    public ResponseEntity<Page<AdminRole>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) String roleName,
                                                   @RequestParam(required = false) String roleCode,
                                                   @RequestParam(required = false) Integer status) {
        return ResponseEntity.success(adminRoleService.getPage(pageNo, pageSize, roleName, roleCode, status));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "角色ID", required = true)
    }, summary = "获取角色详情", description = "获取角色详情")
    @GetMapping("/{id}")
    public ResponseEntity<AdminRole> getAdminRole(@PathVariable Integer id) {
        return ResponseEntity.success(adminRoleService.getById(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "角色ID", required = true)
    }, summary = "删除角色", description = "删除角色")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(adminRoleService.removeById(id));
    }

    @Operation(summary = "添加角色", description = "添加角色")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody AdminRole adminRole) {
        return ResponseEntity.success(adminRoleService.save(adminRole));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "角色ID", required = true)
    }, summary = "修改角色", description = "修改角色")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody AdminRole adminRole) {
        adminRole.setId(id);
        return ResponseEntity.success(adminRoleService.updateById(adminRole));
    }

    @Operation(summary = "角色下拉列表", description = "角色下拉列表")
    @GetMapping("/roles-options")
    public ResponseEntity<List<AdminRoleOptionVo>> getRoleOptions(){
        return ResponseEntity.success(adminRoleService.getRoleOptions());
    }

}
