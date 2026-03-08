package com.sweet.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminRole;
import com.sweet.admin.vo.AdminRoleOptionVo;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminRoleService extends BaseService<AdminRole> {

    /**
     * 角色分页查询
     * @param pageNo
     * @param pageSize
     * @param roleName
     * @param roleCode
     * @param status
     *
     * @return
     */
    Page<AdminRole> getPage(Integer pageNo, Integer pageSize, String roleName, String roleCode, Integer status);

    /**
     * 根据角色id列表查询角色信息
     * @param adminRoleIds
     * @return
     */
    List<AdminRole> getRolesByIds(List<Integer> adminRoleIds);

    /**
     * 根据角色id列表查询角色编码信息
     * @param adminRoleIds
     * @return
     */
    List<String> getRoleCodesByRoleIds(List<Integer> adminRoleIds);

    /**
     * 获取角色下拉列表
     * @return
     */
    List<AdminRoleOptionVo> getRoleOptions();
}
