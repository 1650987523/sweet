package com.sweet.admin.service;

import com.sweet.admin.entity.AdminMenu;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminAggregationService extends BaseService {

    /**
     * 根据角色id查询角色菜单信息
     * @param id
     * @return
     */
    List<AdminMenu> getMenusByRoleId(Integer id);

    /**
     * 删除权限及其关联关系
     * @param id 权限ID
     * @return 是否成功
     */
    Boolean deletePermissionWithRelations(Integer id);

    /**
     * 删除用户及其关联关系
     * @param id 用户ID
     * @return 是否成功
     */
    Boolean deleteUserWithRelations(Integer id);

    /**
     * 删除角色及其关联关系
     * @param id 角色ID
     * @return 是否成功
     */
    Boolean deleteRoleWithRelations(Integer id);

    /**
     * 删除部门及其关联关系
     * @param id 部门ID
     * @return 是否成功
     */
    Boolean deleteDeptWithRelations(Integer id);
}
