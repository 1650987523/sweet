package com.sweet.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminPermission;
import com.sweet.admin.vo.AdminPermIdNameOptionVo;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminPermissionService extends BaseService<AdminPermission> {

    /**
     * 分页查询权限列表
     * @param pageNo
     * @param pageSize
     * @param permName
     * @return
     */
    Page<AdminPermission> getPage(Integer pageNo, Integer pageSize, String permName);

    /**
     * 根据权限id列表查询权限列表
     * @param adminPermissionIds
     * @return
     */
    List<AdminPermission> getPermissionsByIds(List<Integer> adminPermissionIds);

    /**
     * 根据权限id列表查询权限标识列表
     * @param adminPermissionIds
     * @return
     */
    List<String> getPermCodesByIds(List<Integer> adminPermissionIds);

    /**
     * 获取group后的权限code编码列表
     * @return
     */
    List<String> getPermCodes();

    /**
     * 获取权限信息列表根据权限编码列表
     * @param permCodes
     * @return
     */
    List<AdminPermission> getPermissionsByPermCodes(List<String> permCodes);

    /**
     * 根据权限编码列表获取权限id列表
     * @param permCodes
     * @return
     */
    List<Integer> getPermissionIdsByPermCodes(List<String> permCodes);

    /**
     * 权限管理下拉接口-id-name-options
     * @return
     */
    List<AdminPermIdNameOptionVo> getIdNameOptions();

    /**
     * 获取 HTTP 接口权限列表（有 url 配置的权限）
     * @return
     */
    List<AdminPermission> getApiPermissions();
}
