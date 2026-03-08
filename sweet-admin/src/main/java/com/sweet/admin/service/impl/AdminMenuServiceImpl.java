package com.sweet.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.admin.entity.AdminMenu;
import com.sweet.admin.mapper.AdminMenuMapper;
import com.sweet.admin.service.*;
import com.sweet.admin.vo.AdminMenuNodeVo;
import com.sweet.common.enums.MenuTypeEnum;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AdminMenuServiceImpl extends BaseServiceImpl<AdminMenuMapper, AdminMenu> implements AdminMenuService {

    private final AdminUserRoleService adminUserRoleService;
    private final AdminRolePermissionService adminRolePermissionService;
    private final AdminPermissionService adminPermissionService;

    @Override
    public Page<AdminMenu> getPage(Integer pageNo, Integer pageSize, String menuName) {
        Page<AdminMenu> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AdminMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.hasText(menuName), AdminMenu::getTitle, menuName);
        return super.page(page, queryWrapper);
    }

    @Override
    public List<AdminMenuNodeVo> getAdminMenuTreeByPerms(List<String> perms) {
        List<AdminMenu> adminMenus = this.getAdminMenusByPerms(perms);


        if(CollectionUtils.isEmpty(adminMenus)){
            return List.of();
        }
        return buildMenuTree(adminMenus);
    }

    @Override
    public List<AdminMenu> getAdminMenusByPerms(List<String> perms) {
        if(CollectionUtils.isEmpty(perms)){
            return List.of();
        }

        QueryWrapper<AdminMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(AdminMenu::getPerms, perms);
        return super.list(queryWrapper);
    }

    @Override
    public List<AdminMenuNodeVo> getMenuTree() {
        QueryWrapper<AdminMenu> queryWrapper = new QueryWrapper<>();
        List<AdminMenu> adminMenus = super.list(queryWrapper);
        return buildMenuTree(adminMenus);
    }

    @Override
    public List<String> getMenuButtonsByCurrentUserToken() {
        Integer userId = StpUtil.getLoginIdAsInt();
        List<Integer> adminRoleIds = adminUserRoleService.getRoleIdsByUserId(userId);

        List<String> adminPermissionPerms = adminRolePermissionService.getPermCodesByRoleIds(adminRoleIds);
        this.getMenuButtonsByPerms(adminPermissionPerms);
        return List.of();
    }

    @Override
    public List<AdminMenu> getMenuButtonsByPerms(List<String> adminPermissionPerms) {
        if(CollectionUtils.isEmpty(adminPermissionPerms)){
            return List.of();
        }

        QueryWrapper<AdminMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(CollectionUtils.isEmpty(adminPermissionPerms), AdminMenu::getPerms, adminPermissionPerms);
        queryWrapper.lambda().eq(AdminMenu::getMenuType, MenuTypeEnum.FUNCTION_BUTTON.getCode());
        return List.of();
    }

    @Override
    public List<AdminMenuNodeVo> getRouterMenuTreeByCurrentUserToken() {
        Integer userId = StpUtil.getLoginIdAsInt();
        List<Integer> adminRoleIds = adminUserRoleService.getRoleIdsByUserId(userId);
        List<String> adminPermissionPerms = adminRolePermissionService.getPermCodesByRoleIds(adminRoleIds);
        List<AdminMenuNodeVo> adminMenuTreeByPerms = this.getAdminRouterMenuTreeByPerms(adminPermissionPerms);
        return adminMenuTreeByPerms;
    }

    @Override
    public List<AdminMenuNodeVo> getAdminRouterMenuTreeByPerms(List<String> adminPermissionPerms) {
        List<AdminMenu> adminMenus = this.getAdminMenusByPerms(adminPermissionPerms);


        if(CollectionUtils.isEmpty(adminMenus)){
            return List.of();
        }
        List<AdminMenu> filterMenus = adminMenus.stream()
                .filter(menu -> !MenuTypeEnum.FUNCTION_BUTTON.getCode().equals(menu.getMenuType()))
                .collect(Collectors.toList());

        return buildMenuTree(filterMenus);
    }

    @Override
    public List<AdminMenu> getMenusByPermCodes(List<String> permCodes) {
        if(CollectionUtils.isEmpty(permCodes)){
            return List.of();
        }

        QueryWrapper<AdminMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(AdminMenu::getPerms, permCodes);
        return super.list(queryWrapper);
    }

    public static List<AdminMenuNodeVo> buildMenuTree(List<AdminMenu> adminMenus){
        if(CollectionUtils.isEmpty(adminMenus)){
            return List.of();
        }


        //数据结构转换
        List<AdminMenuNodeVo> adminMenuNodeVos = new ArrayList<>();
        Map<Integer, AdminMenuNodeVo> adminMenuNodeVoMap = new HashMap<>();
        for (AdminMenu adminMenu : adminMenus) {
            AdminMenuNodeVo adminMenuNodeVo = new AdminMenuNodeVo();
            BeanUtils.copyProperties(adminMenu, adminMenuNodeVo);
            adminMenuNodeVos.add(adminMenuNodeVo);
            adminMenuNodeVoMap.put(adminMenu.getId(), adminMenuNodeVo);
        }

        //构建父子关系
        List<AdminMenuNodeVo> adminMenuTrees = new ArrayList<>();
        for (AdminMenuNodeVo adminMenuNodeVo : adminMenuNodeVos) {

            //根节点处理
            Integer id = adminMenuNodeVo.getId();
            Integer parentId = adminMenuNodeVo.getParentId();
            if(Objects.isNull(parentId)){
                adminMenuTrees.add(adminMenuNodeVoMap.get(id));
                continue;
            }

            //子节点处理
            AdminMenuNodeVo parentNode = adminMenuNodeVoMap.get(parentId);
            if(Objects.nonNull(parentNode)){
                parentNode.getChildren().add(adminMenuNodeVo);
            }else {
                adminMenuTrees.add(adminMenuNodeVoMap.get(id));
            }
        }

        //补全是否存在子节点状态
        for (AdminMenuNodeVo adminMenuNodeVo : adminMenuTrees) {
            List<AdminMenuNodeVo> children = adminMenuNodeVo.getChildren();
            if(CollectionUtils.isEmpty(children)){
                continue;
            }
            adminMenuNodeVo.setHasChildren(true);
        }
        
         return adminMenuTrees;
    }

}
