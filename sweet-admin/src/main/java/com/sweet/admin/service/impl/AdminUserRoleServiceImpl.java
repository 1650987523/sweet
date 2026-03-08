package com.sweet.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sweet.admin.entity.AdminRole;
import com.sweet.admin.entity.AdminUserRole;
import com.sweet.admin.mapper.AdminUserRoleMapper;
import com.sweet.admin.service.AdminRoleService;
import com.sweet.admin.service.AdminUserRoleService;
import com.sweet.admin.vo.AdminRoleOptionVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AdminUserRoleServiceImpl extends BaseServiceImpl<AdminUserRoleMapper, AdminUserRole> implements AdminUserRoleService {

    private final AdminRoleService adminRoleService;

    @Override
    public List<Integer> getRoleIdsByUserId(Integer userId) {
        QueryWrapper<AdminUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUserRole::getUserId, userId);
        List<AdminUserRole> userRoles = super.list(queryWrapper);

        List<AdminUserRole> adminUserRoles = CollectionUtils.isEmpty(userRoles)
                ? List.of()
                : userRoles;

        return adminUserRoles.stream().map(AdminUserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public List<AdminRoleOptionVo> getRoleOptionsByUserId(Integer userId) {

        // 1. 获取用户关联的角色ID列表
        List<Integer> roleIds = this.getRoleIdsByUserId(userId);

        if (CollectionUtils.isEmpty(roleIds)) {
            return List.of();
        }

        // 2. 根据角色ID列表获取角色信息
        List<AdminRole> roles = adminRoleService.listByIds(roleIds);

        if (CollectionUtils.isEmpty(roles)) {
            return List.of();
        }

        // 3. 转换为VO
        List<AdminRoleOptionVo> roleOptions = new ArrayList<>(roles.size());
        for (AdminRole role : roles) {
            AdminRoleOptionVo roleOption = new AdminRoleOptionVo();
            roleOption.setId(role.getId()).setRoleName(role.getRoleName());
            roleOptions.add(roleOption);
        }

        return roleOptions;
    }

    @Override
    public Boolean removeByUserId(Integer userId) {
        QueryWrapper<AdminUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUserRole::getUserId, userId);
        return super.remove(queryWrapper);
    }

    @Override
    public Boolean removeByRoleId(Integer roleId) {
        QueryWrapper<AdminUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUserRole::getRoleId, roleId);
        return super.remove(queryWrapper);
    }

    @Override
    public Boolean setRoleIdsByUserId(Integer userId, List<Integer> roleIds) {
        log.info("setRoleIdsByUserId userId:{}, roleIds:{}", userId, roleIds);

        // 1. 删除原有的用户-角色关联
        this.removeByUserId(userId);

        // 2. 如果角色列表为空，表示清空该用户的所有角色
        if (CollectionUtils.isEmpty(roleIds)) {
            return true;
        }

        // 3. 批量插入新的用户-角色关联
        List<AdminUserRole> userRoles = new ArrayList<>(roleIds.size());
        for (Integer roleId : roleIds) {
            AdminUserRole adminUserRole = new AdminUserRole();
            adminUserRole.setUserId(userId);
            adminUserRole.setRoleId(roleId);
            userRoles.add(adminUserRole);
        }
        return super.saveBatch(userRoles);
    }
}
