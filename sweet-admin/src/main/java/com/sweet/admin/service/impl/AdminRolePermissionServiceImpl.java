package com.sweet.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sweet.admin.entity.AdminPermission;
import com.sweet.admin.entity.AdminRolePermission;
import com.sweet.admin.mapper.AdminRolePermissionMapper;
import com.sweet.admin.service.AdminPermissionService;
import com.sweet.admin.service.AdminRolePermissionService;
import com.sweet.admin.vo.AdminPermIdNameOptionVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AdminRolePermissionServiceImpl extends BaseServiceImpl<AdminRolePermissionMapper, AdminRolePermission> implements AdminRolePermissionService {

    private final AdminPermissionService adminPermissionService;

    @Override
    public Boolean setPermissionIdsByRoleId(Integer roleId, List<String> permCodes) {
        log.info("setPermissionIdsByRoleId roleId:{}, permCodes:{}", roleId, permCodes.toArray());

        //删除原来的数据
        this.removeByRoleId(roleId);

        //设置当前角色没有任何权限生效
        if (CollectionUtils.isEmpty(permCodes)) {
            return true;
        }

        //更新角色权限
        List<Integer> adminPermissionIds = adminPermissionService.getPermissionIdsByPermCodes(permCodes);
        if(CollectionUtils.isEmpty(adminPermissionIds)){
            return true;
        }

        List<AdminRolePermission> rolePermissions = new ArrayList<>(adminPermissionIds.size());
        for (Integer permissionId : adminPermissionIds) {
            AdminRolePermission adminRolePermission = new AdminRolePermission();
            adminRolePermission.setRoleId(roleId);
            adminRolePermission.setPermissionId(permissionId);
            rolePermissions.add(adminRolePermission);
        }
        return super.saveBatch(rolePermissions);
    }

    @Override
    public List<AdminRolePermission> getRolePermissionsByRoleId(Integer id) {
        QueryWrapper<AdminRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminRolePermission::getRoleId, id);
        return super.list(queryWrapper);
    }

    @Override
    public List<AdminRolePermission> getRolePermissionsByRoleIds(List<Integer> adminRoleIds) {
        QueryWrapper<AdminRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(AdminRolePermission::getRoleId, adminRoleIds);
        return super.list(queryWrapper);
    }

    @Override
    public List<Integer> getPermissionIdsByRoleIds(List<Integer> adminRoleIds) {
        List<AdminRolePermission> adminRolePermissions = this.getRolePermissionsByRoleIds(adminRoleIds);

        if(CollectionUtils.isEmpty(adminRolePermissions)){
            return  List.of();
        }

        return adminRolePermissions.stream()
                .map(AdminRolePermission::getPermissionId)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getPermCodesByRoleIds(List<Integer> adminRoleIds) {
        List<Integer> adminPermissionIds = this.getPermissionIdsByRoleIds(adminRoleIds);

        if(CollectionUtils.isEmpty(adminPermissionIds)){
            return  List.of();
        }

        return adminPermissionService.getPermCodesByIds(adminPermissionIds);
    }

    @Override
    public List<Integer> getPermissionIdsByRoleId(Integer id) {
        List<AdminRolePermission> adminRolePermissions = this.getRolePermissionsByRoleId(id);
        if(CollectionUtils.isEmpty(adminRolePermissions)){
            return  List.of();
        }

        return adminRolePermissions.stream()
                .map(AdminRolePermission::getPermissionId)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminPermIdNameOptionVo> getPermissionOptionsByRoleId(Integer roleId) {
        // 1. 获取角色关联的权限ID列表
        List<Integer> permissionIds = this.getPermissionIdsByRoleId(roleId);

        if (CollectionUtils.isEmpty(permissionIds)) {
            return List.of();
        }

        // 2. 根据权限ID列表获取权限信息
        List<AdminPermission> permissions = adminPermissionService.listByIds(permissionIds);

        if (CollectionUtils.isEmpty(permissions)) {
            return List.of();
        }

        // 3. 转换为VO
        List<AdminPermIdNameOptionVo> permissionOptions = new ArrayList<>(permissions.size());
        for (AdminPermission permission : permissions) {
            AdminPermIdNameOptionVo permissionOption = new AdminPermIdNameOptionVo();
            permissionOption.setId(permission.getId());
            permissionOption.setPermName(permission.getPermName());
            permissionOptions.add(permissionOption);
        }

        return permissionOptions;
    }

    @Override
    public Boolean removeByRoleId(Integer roleId) {
        QueryWrapper<AdminRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminRolePermission::getRoleId, roleId);
        return super.remove(queryWrapper);
    }

    @Override
    public Boolean removeByPermissionId(Integer permissionId) {
        QueryWrapper<AdminRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminRolePermission::getPermissionId, permissionId);
        return super.remove(queryWrapper);
    }


}
