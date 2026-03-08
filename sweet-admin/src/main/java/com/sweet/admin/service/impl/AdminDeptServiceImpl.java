package com.sweet.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminDept;
import com.sweet.admin.mapper.AdminDeptMapper;
import com.sweet.admin.service.AdminDeptService;
import com.sweet.admin.vo.AdminDeptNodeVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class AdminDeptServiceImpl extends BaseServiceImpl<AdminDeptMapper, AdminDept> implements AdminDeptService {

    @Override
    public Page<AdminDept> getPage(Integer pageNo, Integer pageSize, String deptName) {
        Page<AdminDept> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AdminDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.hasText(deptName), AdminDept::getDepartmentName, deptName);
        return super.page(page, queryWrapper);
    }

    @Override
    public List<AdminDeptNodeVo> getDeptTree(String departmentName, Integer status) {

        // 1. 查询所有部门
        QueryWrapper<AdminDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.hasText(departmentName), AdminDept::getDepartmentName, departmentName)
                .eq(Objects.nonNull(status), AdminDept::getStatus, status);
        List<AdminDept> allDepts = super.list(queryWrapper);

        if (CollectionUtils.isEmpty(allDepts)) {
            return List.of();
        }
        
        // 2. 构建树形结构
        return buildDeptTree(allDepts);
    }

    /**
     * 构建部门树形结构
     * @param adminDepts 部门列表
     * @return 部门树形结构
     */
    private List<AdminDeptNodeVo> buildDeptTree(List<AdminDept> adminDepts) {
        if (CollectionUtils.isEmpty(adminDepts)) {
            return List.of();
        }

        // 数据结构转换
        List<AdminDeptNodeVo> adminDeptNodeVos = new ArrayList<>();
        Map<Integer, AdminDeptNodeVo> adminDeptNodeVoMap = new HashMap<>();
        for (AdminDept adminDept : adminDepts) {
            AdminDeptNodeVo adminDeptNodeVo = new AdminDeptNodeVo();
            BeanUtils.copyProperties(adminDept, adminDeptNodeVo);
            adminDeptNodeVos.add(adminDeptNodeVo);
            adminDeptNodeVoMap.put(adminDept.getId(), adminDeptNodeVo);
        }

        // 构建父子关系
        List<AdminDeptNodeVo> adminDeptTrees = new ArrayList<>();
        for (AdminDeptNodeVo adminDeptNodeVo : adminDeptNodeVos) {
            Integer id = adminDeptNodeVo.getId();
            Integer parentId = adminDeptNodeVo.getParentId();
            
            // 根节点处理（parentId为空或空字符串）
            if (Objects.isNull(parentId)) {
                adminDeptTrees.add(adminDeptNodeVoMap.get(id));
                continue;
            }

            // 子节点处理
            try {
                AdminDeptNodeVo parentNode = adminDeptNodeVoMap.get(parentId);
                if (Objects.nonNull(parentNode)) {
                    parentNode.getChildren().add(adminDeptNodeVo);
                } else {
                    // 如果找不到父节点，作为根节点处理
                    adminDeptTrees.add(adminDeptNodeVoMap.get(id));
                }
            } catch (NumberFormatException e) {
                // parentId不是有效数字，作为根节点处理
                adminDeptTrees.add(adminDeptNodeVoMap.get(id));
            }
        }

        // 补全是否存在子节点状态
        for (AdminDeptNodeVo adminDeptNodeVo : adminDeptTrees) {
            List<AdminDeptNodeVo> children = adminDeptNodeVo.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                adminDeptNodeVo.setHasChildren(true);
            }
        }

        return adminDeptTrees;
    }
}
