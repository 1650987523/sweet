package com.sweet.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminDept;
import com.sweet.admin.vo.AdminDeptNodeVo;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminDeptService extends BaseService<AdminDept> {

    /**
     * 获取部门分页列表
     * @param pageNo
     * @param pageSize
     * @param deptName
     * @return
     */
    Page<AdminDept> getPage(Integer pageNo, Integer pageSize, String deptName);

    /**
     * 获取部门树
     * @param departmentName 部门名称
     * @param status 状态
     */
    List<AdminDeptNodeVo> getDeptTree(String departmentName, Integer status);
}
