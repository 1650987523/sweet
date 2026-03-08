package com.sweet.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminRole;
import com.sweet.admin.mapper.AdminRoleMapper;
import com.sweet.admin.service.AdminRoleService;
import com.sweet.admin.vo.AdminRoleOptionVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    @Override
    public Page<AdminRole> getPage(Integer pageNo, Integer pageSize, String roleName, String roleCode, Integer status) {
        Page<AdminRole> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.hasText(roleName), AdminRole::getRoleName, roleName);
        queryWrapper.lambda().like(StringUtils.hasText(roleCode), AdminRole::getRoleCode, roleCode);
        queryWrapper.lambda().eq(Objects.nonNull(status), AdminRole::getStatus, status);
        return super.page(page, queryWrapper);
    }

    @Override
    public List<AdminRole> getRolesByIds(List<Integer> adminRoleIds) {
        if(CollectionUtils.isEmpty(adminRoleIds)){
            return List.of();
        }

        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(AdminRole::getId, adminRoleIds);
        return super.list(queryWrapper);
    }

    @Override
    public List<String> getRoleCodesByRoleIds(List<Integer> adminRoleIds) {
        List<AdminRole> adminRoles = this.getRolesByIds(adminRoleIds);

        if(CollectionUtils.isEmpty(adminRoles)){
            return List.of();
        }
        return adminRoles.stream().map(AdminRole::getRoleCode).toList();
    }

    @Override
    public List<AdminRoleOptionVo> getRoleOptions() {
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(AdminRole::getId, AdminRole::getRoleName);
        List<AdminRole> adminRoles = super.list(queryWrapper);

        if(CollectionUtils.isEmpty(adminRoles)){
            return List.of();
        }

        List<AdminRoleOptionVo> roleOptions = new ArrayList<>(adminRoles.size());
        for (AdminRole adminRole : adminRoles) {
            AdminRoleOptionVo adminRoleOptionVo = new AdminRoleOptionVo();
            adminRoleOptionVo.setId(adminRole.getId()).setRoleName(adminRole.getRoleName());
            roleOptions.add(adminRoleOptionVo);
        }

        return roleOptions;
    }

    @Override
    public boolean save(AdminRole entity) {
        log.info("save AdminRole:{}", entity);

        String roleName = entity.getRoleName();
        String roleCode = entity.getRoleCode();
        Assert.hasText(roleCode, "角色编码不能为空");
        Assert.hasText(roleName, "角色名称不能为空");

        entity.setRoleCode(roleCode.toUpperCase());
        return super.save(entity);
    }

    @Override
    public boolean updateById(AdminRole entity) {
        // 角色编码转为大写
        if (StringUtils.hasText(entity.getRoleCode())) {
            entity.setRoleCode(entity.getRoleCode().toUpperCase());
        }
        return super.updateById(entity);
    }
}
