package com.sweet.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminDept;
import com.sweet.admin.service.AdminDeptService;
import com.sweet.admin.vo.AdminDeptNodeVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台部门管理")
@RequestMapping("/dept")
@RestController
@AllArgsConstructor
public class AdminDeptController {

    private final AdminDeptService adminDeptService;

    @Operation(parameters =  {
            @Parameter(name = "pageNo", description = "页码"),
            @Parameter(name = "pageSize", description = "每页数量"),
            @Parameter(name = "deptName", description = "部门名称")
    },summary = "部门列表", description = "部门列表")
    @GetMapping
    public ResponseEntity<Page<AdminDept>> getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam (required = false) String deptName) {
        return ResponseEntity.success(adminDeptService.getPage(pageNo, pageSize, deptName));
    }

    @Operation(parameters = {
            @Parameter(name = "departmentName", description = "部门名称", required = false),
            @Parameter(name = "status", description = "状态", required = false)
    }, summary = "获取部门树", description = "获取部门树形结构")
    @GetMapping("/tree")
    public ResponseEntity<List<AdminDeptNodeVo>> getDeptTree(@RequestParam(required = false) String departmentName,
                                                             @RequestParam(required = false) Integer status) {
        return ResponseEntity.success(adminDeptService.getDeptTree(departmentName, status));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "部门ID", required = true)
    }, summary = "获取部门详情", description = "获取部门详情")
    @GetMapping("/{id}")
    public ResponseEntity<AdminDept> getAdminDept(@PathVariable Integer id) {
        return ResponseEntity.success(adminDeptService.getById(id));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "部门ID", required = true)
    }, summary = "删除部门", description = "删除部门")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.success(adminDeptService.removeById(id));
    }

    @Operation(summary = "添加部门", description = "添加部门")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody AdminDept adminDept) {
        return ResponseEntity.success(adminDeptService.save(adminDept));
    }

    @Operation(parameters = {
            @Parameter(name = "id", description = "部门ID", required = true)
    }, summary = "修改部门", description = "修改部门")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody AdminDept adminDept) {
        adminDept.setId(id);
        return ResponseEntity.success(adminDeptService.updateById(adminDept));
    }

}
