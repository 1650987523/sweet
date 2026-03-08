package com.sweet.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminPermission;
import com.sweet.admin.mapper.AdminPermissionMapper;
import com.sweet.admin.service.AdminPermissionService;
import com.sweet.admin.vo.AdminPermIdNameOptionVo;
import com.sweet.common.enums.PermissionTypeEnum;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AdminPermissionServiceImpl extends BaseServiceImpl<AdminPermissionMapper, AdminPermission> implements AdminPermissionService {

    @Override
    public Page<AdminPermission> getPage(Integer pageNo, Integer pageSize, String permName) {
        Page<AdminPermission> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AdminPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.hasText(permName), AdminPermission::getPermName, permName);
        queryWrapper.lambda().orderByAsc(AdminPermission::getId);
        return super.page(page, queryWrapper);
    }

    @Override
    public List<AdminPermission> getPermissionsByIds(List<Integer> adminPermissionIds) {
        if(CollectionUtils.isEmpty(adminPermissionIds)){
            return List.of();
        }

        QueryWrapper<AdminPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(AdminPermission::getId, adminPermissionIds);
        return super.list(queryWrapper);
    }

    @Override
    public List<String> getPermCodesByIds(List<Integer> adminPermissionIds) {
        if(CollectionUtils.isEmpty(adminPermissionIds)){
            return List.of();
        }

        List<AdminPermission> permissions = this.getPermissionsByIds(adminPermissionIds);

        return permissions.stream()
                .filter(permission -> Objects.nonNull(permission))
                .map(AdminPermission::getPermCode)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getPermCodes() {
        QueryWrapper<AdminPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().groupBy(AdminPermission::getPermCode).select(AdminPermission::getPermCode);
        List<AdminPermission> adminPermissions = super.list(queryWrapper);
        return adminPermissions.stream().map(AdminPermission::getPermCode)
                .filter(permCode -> StringUtils.hasText(permCode)).toList();
    }

    @Override
    public List<AdminPermission> getPermissionsByPermCodes(List<String> permCodes) {
        if(CollectionUtils.isEmpty(permCodes)){
            return List.of();
        }

        QueryWrapper<AdminPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(AdminPermission::getPermCode, permCodes);
        return super.list(queryWrapper);
    }

    @Override
    public List<Integer> getPermissionIdsByPermCodes(List<String> permCodes) {
        List<AdminPermission> adminPermissions = this.getPermissionsByPermCodes(permCodes);
        return Optional.ofNullable(adminPermissions)
                .orElse(Collections.emptyList())
                .stream()
                .map(AdminPermission::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminPermIdNameOptionVo> getIdNameOptions() {
        QueryWrapper<AdminPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(AdminPermission::getId, AdminPermission::getPermName);
        List<AdminPermission> adminPermissions = super.list(queryWrapper);

        if(CollectionUtils.isEmpty(adminPermissions)){
            return List.of();
        }

        List<AdminPermIdNameOptionVo> results = new ArrayList<>(adminPermissions.size());
        for (AdminPermission adminPermission : adminPermissions) {
            AdminPermIdNameOptionVo adminPermIdNameOptionVo = new AdminPermIdNameOptionVo();
            adminPermIdNameOptionVo.setId(adminPermission.getId());
            adminPermIdNameOptionVo.setPermName(adminPermission.getPermName());
            results.add(adminPermIdNameOptionVo);
        }

        return results;
    }

    @Override
    public List<AdminPermission> getApiPermissions() {
        QueryWrapper<AdminPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminPermission::getPermType, PermissionTypeEnum.API.getCode());
        return super.list(queryWrapper);
    }
}
