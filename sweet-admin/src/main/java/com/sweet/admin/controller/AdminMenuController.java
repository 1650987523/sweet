package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminMenu;
import com.sweet.admin.service.AdminMenuService;
import com.sweet.admin.vo.AdminMenuNodeVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台菜单管理")
@RequestMapping("/menu")
@RestController
@AllArgsConstructor
public class AdminMenuController {

    private final AdminMenuService adminMenuService;

    @Operation(parameters =  {
            @Parameter(name = "pageNo", description = "页码"),
            @Parameter(name = "pageSize", description = "每页数量"),
            @Parameter(name = "menuName", description = "菜单名称")
    },summary = "菜单列表", description = "菜单列表")
    @GetMapping
    public ResponseEntity<Page<AdminMenu>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) String menuName) {
        return ResponseEntity.success(adminMenuService.getPage(pageNo, pageSize, menuName));
    }

    @Operation(summary = "获取当前用户菜单树(前端左侧路由)", description = "获取当前用户菜单树(前端左侧路由)")
    @GetMapping("/current-user/router/tree")
    public ResponseEntity<List<AdminMenuNodeVo>> getRouterMenuTree() {
        return ResponseEntity.success(adminMenuService.getRouterMenuTreeByCurrentUserToken());
    }

    @Operation(summary = "获取菜单表的菜单树", description = "获取菜单表的菜单树")
    @GetMapping("/tree")
    public ResponseEntity<List<AdminMenuNodeVo>> getMenuTree() {
        return ResponseEntity.success(adminMenuService.getMenuTree());
    }

    @Operation(summary = "获取当前用户菜单按钮列表", description = "获取当前用户菜单按钮列表")
    @GetMapping("/button/list")
    public ResponseEntity<List<String>> getMenuButtons() {
        return ResponseEntity.success(adminMenuService.getMenuButtonsByCurrentUserToken());
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "菜单ID", required = true)
    }, summary = "获取菜单详情", description = "获取菜单详情")
    @GetMapping("/{id}")
    public ResponseEntity<AdminMenu> getAdminMenu(@PathVariable Integer id) {
        return ResponseEntity.success(adminMenuService.getById(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "菜单ID", required = true)
    }, summary = "删除菜单", description = "删除菜单")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(adminMenuService.removeById(id));
    }

    @Operation(summary = "添加菜单", description = "添加菜单")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody AdminMenu adminMenu) {
        return ResponseEntity.success(adminMenuService.save(adminMenu));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "菜单ID", required = true)
    }, summary = "修改菜单", description = "修改菜单")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody AdminMenu adminMenu) {
        adminMenu.setId(id);
        return ResponseEntity.success(adminMenuService.updateById(adminMenu));
    }
}
