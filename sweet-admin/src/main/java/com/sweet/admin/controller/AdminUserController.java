package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.sweet.admin.dto.LoginDto;
import com.sweet.admin.entity.AdminUser;
import com.sweet.admin.service.AdminUserService;
import com.sweet.admin.vo.AdminUserVO;
import com.sweet.admin.vo.AdminUserInfoVo;
import com.sweet.admin.vo.LoginResponseVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台用户管理")
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseVo> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.success(adminUserService.login(loginDto));
    }

    @Operation(parameters = {
            @Parameter(name = "userId", description = "用户 ID"),
            @Parameter(name = "username", description = "用户名")
    }, summary = "刷新 token", description = "刷新 token")
    @PostMapping("/refresh-token")
    public ResponseEntity<JsonNode> refreshToken(@RequestParam String userId, @RequestParam String username) {
        return ResponseEntity.success(adminUserService.refreshToken(userId, username));
    }

    @Operation(parameters = {
    }, summary = "获取用户信息接口", description = "获取用户信息接口")
    @GetMapping("/info")
    public ResponseEntity<AdminUserInfoVo> getUserInfo() {
        return ResponseEntity.success(adminUserService.getUserInfoByCurrentToken());
    }

    @Operation(parameters = {
            @Parameter(name = "pageNo", description = "页码"),
            @Parameter(name = "pageSize", description = "每页数量"),
            @Parameter(name = "username", description = "用户名"),
            @Parameter(name = "mobile", description = "手机号"),
            @Parameter(name = "status", description = "用户状态"),
            @Parameter(name = "storeId", description = "门店 ID"),
            @Parameter(name = "type", description = "账号类型 1-管理员 2-普通用户")
    }, summary = "用户分页接口", description = "用户分页接口（包含角色信息）")
    @GetMapping
    public ResponseEntity<Page<AdminUserVO>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false) String username,
                                                   @RequestParam(required = false) String mobile,
                                                   @RequestParam(required = false) Integer status,
                                                   @RequestParam(required = false) Long storeId,
                                                   @RequestParam(required = false) Integer type) {
        return ResponseEntity.success(adminUserService.getPage(pageNo, pageSize, username, mobile, status, storeId, type));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "用户 ID")
    }, summary = "用户详情接口", description = "用户详情接口")
    @GetMapping("/{id}")
    public ResponseEntity<AdminUser> getAdminUser(@PathVariable Integer id) {
        return ResponseEntity.success(adminUserService.getAdminUserById(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "用户 ID")
    }, summary = "用户删除接口", description = "用户删除接口")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAdminUser(@PathVariable Integer id) {
        return ResponseEntity.success(adminUserService.removeById(id));
    }

    @Operation(summary = "用户添加接口", description = "用户添加接口")
    @PostMapping
    public ResponseEntity<Boolean> addAdminUser(@RequestBody AdminUser adminUser) {
        return ResponseEntity.success(adminUserService.save(adminUser));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "用户 ID")
    }, summary = "用户修改接口", description = "用户修改接口")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateAdminUser(@PathVariable Integer id, @RequestBody AdminUser adminUser) {
        adminUser.setId(id);
        return ResponseEntity.success(adminUserService.updateAdminUser(adminUser));
    }

}
