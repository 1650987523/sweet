package com.sweet.admin.controller;

import com.sweet.admin.service.AdminUserDeptService;
import com.sweet.admin.vo.AdminDeptOptionVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户部门关系管理")
@RequestMapping("/user-dept")
@RestController
@AllArgsConstructor
public class AdminUserDeptController {

    private final AdminUserDeptService adminUserDeptService;

    @Operation(parameters = {
            @Parameter(name = "userId", description = "用户ID", required = true)
    }, summary = "获取用户部门ID列表", description = "获取用户部门ID列表")
    @GetMapping("/{userId}/dept-ids")
    public ResponseEntity<List<Integer>> getDeptIdsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.success(adminUserDeptService.getDeptIdsByUserId(userId));
    }

    @Operation(parameters = {
            @Parameter(name = "userId", description = "用户ID", required = true)
    }, summary = "获取用户部门选项列表", description = "返回用户关联的部门ID和部门名称")
    @GetMapping("/{userId}/dept-options")
    public ResponseEntity<List<AdminDeptOptionVo>> getDeptOptionsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.success(adminUserDeptService.getDeptOptionsByUserId(userId));
    }

    @Operation(parameters = {
            @Parameter(name = "userId", description = "用户ID", required = true)
    }, summary = "设置用户部门", description = "设置用户部门（覆盖式）")
    @PutMapping("/{userId}/depts")
    public ResponseEntity<Boolean> setDeptIdsByUserId(@PathVariable Integer userId, @RequestBody List<Integer> deptIds) {
        return ResponseEntity.success(adminUserDeptService.setDeptIdsByUserId(userId, deptIds));
    }

}
