package com.sweet.admin.service.impl;

import com.sweet.admin.entity.AdminMenu;
import com.sweet.admin.service.*;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AdminAggregationServiceImpl extends BaseServiceImpl implements AdminAggregationService {

    private final AdminRolePermissionService adminRolePermissionService;
    private final AdminPermissionService adminPermissionService;
    private final AdminMenuService adminMenuService;
    private final AdminUserService adminUserService;
    private final AdminUserDeptService adminUserDeptService;
    private final AdminUserRoleService adminUserRoleService;
    private final AdminRoleService adminRoleService;
    private final AdminDeptService adminDeptService;

    @Override
    public List<AdminMenu> getMenusByRoleId(Integer id) {
        List<Integer> permissionIds = adminRolePermissionService.getPermissionIdsByRoleId(id);
        List<String> permCodes = adminPermissionService.getPermCodesByIds(permissionIds);
        return adminMenuService.getMenusByPermCodes(permCodes);
    }

    @Transactional
    @Override
    public Boolean deletePermissionWithRelations(Integer id) {
        log.info("deletePermissionWithRelations permissionId:{}", id);

        // 1. 删除角色-权限关联
        adminRolePermissionService.removeByPermissionId(id);

        // 2. 删除权限本身
        return adminPermissionService.removeById(id);
    }

    @Transactional
    @Override
    public Boolean deleteUserWithRelations(Integer id) {
        log.info("deleteUserWithRelations userId:{}", id);

        // 1. 删除用户-部门关联
        adminUserDeptService.removeByUserId(id);

        // 2. 删除用户-角色关联
        adminUserRoleService.removeByUserId(id);

        // 3. 删除用户本身
        return adminUserService.removeById(id);
    }

    @Transactional
    @Override
    public Boolean deleteRoleWithRelations(Integer id) {
        log.info("deleteRoleWithRelations roleId:{}", id);

        // 1. 删除用户-角色关联
        adminUserRoleService.removeByRoleId(id);

        // 2. 删除角色-权限关联
        adminRolePermissionService.removeByRoleId(id);

        // 3. 删除角色本身
        return adminRoleService.removeById(id);
    }

    @Transactional
    @Override
    public Boolean deleteDeptWithRelations(Integer id) {
        log.info("deleteDeptWithRelations deptId:{}", id);

        // 1. 删除用户-部门关联
        adminUserDeptService.removeByDeptId(id);

        // 2. 删除部门本身
        return adminDeptService.removeById(id);
    }
}
