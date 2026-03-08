package com.sweet.admin.service;

import com.sweet.admin.entity.AdminRolePermission;
import com.sweet.admin.vo.AdminPermIdNameOptionVo;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminRolePermissionService extends BaseService<AdminRolePermission> {

    /**
     * 根据角色id和权限编码列表设置中间表角色对应的权限id列表
     * @param roleId
     * @param permCodes
     * @return
     */
    Boolean setPermissionIdsByRoleId(Integer roleId, List<String> permCodes);

    /**
     * 根据角色id获取权限列表
     * @param id
     * @return
     */
    List<AdminRolePermission> getRolePermissionsByRoleId(Integer id);

    /**
     * 根据角色id列表获取权限列表
     * @param adminRoleIds
     * @return
     */
    List<AdminRolePermission> getRolePermissionsByRoleIds(List<Integer> adminRoleIds);

    /**
     * 根据角色id列表获取权限id列表
     * @param adminRoleIds
     * @return
     */
    List<Integer> getPermissionIdsByRoleIds(List<Integer> adminRoleIds);

    /**
     * 根据角色id列表获取权限code列表
     * @param adminRoleIds
     * @return
     */
    List<String> getPermCodesByRoleIds(List<Integer> adminRoleIds);

    /**
     * 根据角色id获取权限ID列表
     * @param id
     * @return
     */
    List<Integer> getPermissionIdsByRoleId(Integer id);

    /**
     * 根据角色ID获取权限选项列表（ID + 名称）
     *
     * @param roleId 角色ID
     * @return 权限选项列表
     */
    List<AdminPermIdNameOptionVo> getPermissionOptionsByRoleId(Integer roleId);

    /**
     * 根据角色ID删除关联关系
     * @param roleId 角色ID
     * @return 是否成功
     */
    Boolean removeByRoleId(Integer roleId);

    /**
     * 根据权限ID删除关联关系
     * @param permissionId 权限ID
     * @return 是否成功
     */
    Boolean removeByPermissionId(Integer permissionId);

}
