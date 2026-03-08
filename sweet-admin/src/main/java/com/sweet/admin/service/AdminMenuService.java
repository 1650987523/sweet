package com.sweet.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminMenu;
import com.sweet.admin.vo.AdminMenuNodeVo;
import com.sweet.service.service.BaseService;

import java.util.List;

public interface AdminMenuService extends BaseService<AdminMenu> {

    Page<AdminMenu> getPage(Integer pageNo, Integer pageSize, String menuName);

    /**
     * 根据权限标识列表获取菜单树
     * @param perms
     * @return
     */
    List<AdminMenuNodeVo> getAdminMenuTreeByPerms(List<String> perms);

    /**
     * 根据权限标识列表获取菜单列表
     * @param perms
     * @return
     */
    List<AdminMenu> getAdminMenusByPerms(List<String> perms);


    /**
     * 根据token获取当前用户菜单树
     * @return
     */
    List<AdminMenuNodeVo> getMenuTree();

    /**
     * 根据token获取当前用户按钮菜单列表
     * @return
     */
    List<String> getMenuButtonsByCurrentUserToken();

    /**
     * 根据权限标识列表获取按钮菜单列表
     * @param perms
     * @return
     */
    List<AdminMenu> getMenuButtonsByPerms(List<String> perms);

    /**
     * 根据token获取当前用户路由菜单树(去除按钮菜单)
     * @return
     */
    List<AdminMenuNodeVo> getRouterMenuTreeByCurrentUserToken();

    /**
     * 根据权限标识列表获取路由菜单树(去除按钮菜单)
     * @param adminPermissionPerms
     * @return
     */
    List<AdminMenuNodeVo> getAdminRouterMenuTreeByPerms(List<String> adminPermissionPerms);

    /**
     * 根据权限标识列表获取菜单列表
     * @param permCodes
     * @return
     */
    List<AdminMenu> getMenusByPermCodes(List<String> permCodes);
}
