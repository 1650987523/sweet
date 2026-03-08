package com.sweet.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sweet.admin.entity.AdminDept;
import com.sweet.admin.entity.AdminUserDept;
import com.sweet.admin.mapper.AdminUserDeptMapper;
import com.sweet.admin.service.AdminDeptService;
import com.sweet.admin.service.AdminUserDeptService;
import com.sweet.admin.vo.AdminDeptOptionVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminUserDeptServiceImpl extends BaseServiceImpl<AdminUserDeptMapper, AdminUserDept> implements AdminUserDeptService {

    private final AdminDeptService adminDeptService;

    @Override
    public Boolean setDeptIdsByUserId(Integer userId, List<Integer> deptIds) {
        this.removeByUserId(userId);

        if (CollectionUtils.isEmpty(deptIds)) {
            return true;
        }

        List<Integer> distinctDeptIds = deptIds.stream()
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (CollectionUtils.isEmpty(distinctDeptIds)) {
            return true;
        }

        List<AdminUserDept> userDepts = new ArrayList<>(distinctDeptIds.size());
        for (Integer deptId : distinctDeptIds) {
            AdminUserDept adminUserDept = new AdminUserDept();
            adminUserDept.setUserId(userId);
            adminUserDept.setDeptId(deptId);
            userDepts.add(adminUserDept);
        }
        return super.saveBatch(userDepts);
    }

    @Override
    public List<Integer> getDeptIdsByUserId(Integer userId) {
        QueryWrapper<AdminUserDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUserDept::getUserId, userId);
        List<AdminUserDept> userDepts = super.list(queryWrapper);

        if (CollectionUtils.isEmpty(userDepts)) {
            return List.of();
        }

        return userDepts.stream().map(AdminUserDept::getDeptId).collect(Collectors.toList());
    }

    @Override
    public List<AdminDeptOptionVo> getDeptOptionsByUserId(Integer userId) {
        // 1. 获取用户关联的部门ID列表
        List<Integer> deptIds = this.getDeptIdsByUserId(userId);

        if (CollectionUtils.isEmpty(deptIds)) {
            return List.of();
        }

        // 2. 根据部门ID列表获取部门信息
        List<AdminDept> depts = adminDeptService.listByIds(deptIds);

        if (CollectionUtils.isEmpty(depts)) {
            return List.of();
        }

        // 3. 转换为VO
        List<AdminDeptOptionVo> deptOptions = new ArrayList<>(depts.size());
        for (AdminDept dept : depts) {
            AdminDeptOptionVo deptOption = new AdminDeptOptionVo();
            deptOption.setId(dept.getId()).setDepartmentName(dept.getDepartmentName());
            deptOptions.add(deptOption);
        }

        return deptOptions;
    }

    @Override
    public Boolean removeByUserId(Integer userId) {
        QueryWrapper<AdminUserDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUserDept::getUserId, userId);
        return super.remove(queryWrapper);
    }

    @Override
    public Boolean removeByDeptId(Integer deptId) {
        QueryWrapper<AdminUserDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUserDept::getDeptId, deptId);
        return super.remove(queryWrapper);
    }
}
