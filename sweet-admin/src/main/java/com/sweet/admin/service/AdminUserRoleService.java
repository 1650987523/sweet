package com.sweet.admin.service;

import com.sweet.admin.entity.AdminUserRole;
import com.sweet.admin.vo.AdminRoleOptionVo;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminUserRoleService extends BaseService<AdminUserRole> {

    /**
     * 根据用户id获取角色ID列表
     * @param userId
     * @return
     */
    List<Integer> getRoleIdsByUserId(Integer userId);

    /**
     * 根据用户ID获取角色选项列表（ID + 名称）
     *
     * @param userId 用户ID
     * @return 角色选项列表
     */
    List<AdminRoleOptionVo> getRoleOptionsByUserId(Integer userId);

    /**
     * 根据用户ID删除关联关系
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean removeByUserId(Integer userId);

    /**
     * 根据角色ID删除关联关系
     * @param roleId 角色ID
     * @return 是否成功
     */
    Boolean removeByRoleId(Integer roleId);

    /**
     * 根据用户ID设置角色ID列表
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     * @return 是否成功
     */
    Boolean setRoleIdsByUserId(Integer userId, List<Integer> roleIds);
}
