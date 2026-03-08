package com.sweet.admin.service;

import com.sweet.admin.entity.AdminUserDept;
import com.sweet.admin.vo.AdminDeptOptionVo;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminUserDeptService extends BaseService<AdminUserDept> {

    /**
     * 根据用户ID设置部门列表
     *
     * @param userId  用户ID
     * @param deptIds 部门ID列表
     * @return 是否成功
     */
    Boolean setDeptIdsByUserId(Integer userId, List<Integer> deptIds);

    /**
     * 根据用户ID获取部门ID列表
     *
     * @param userId 用户ID
     * @return 部门ID列表
     */
    List<Integer> getDeptIdsByUserId(Integer userId);

    /**
     * 根据用户ID获取部门选项列表（ID + 名称）
     *
     * @param userId 用户ID
     * @return 部门选项列表
     */
    List<AdminDeptOptionVo> getDeptOptionsByUserId(Integer userId);

    /**
     * 根据用户ID删除关联关系
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean removeByUserId(Integer userId);

    /**
     * 根据部门ID删除关联关系
     * @param deptId 部门ID
     * @return 是否成功
     */
    Boolean removeByDeptId(Integer deptId);
}
