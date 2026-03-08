package com.sweet.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.sweet.admin.dto.LoginDto;
import com.sweet.admin.entity.AdminUser;
import com.sweet.admin.vo.AdminUserVO;
import com.sweet.admin.vo.AdminUserInfoVo;
import com.sweet.admin.vo.LoginResponseVo;
import com.sweet.service.service.BaseService;

public interface  AdminUserService extends BaseService<AdminUser> {

    /**
     * 登录
     * @param loginDto
     * @return
     */
    LoginResponseVo login(LoginDto loginDto);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    AdminUser getUserByUsername(String username);

    /**
     * 刷新 token
     * @param userId
     * @param username
     * @return
     */
    JsonNode refreshToken(String userId, String username);

    /**
     * 获取用户分页列表（包含角色信息）
     * @param pageNo
     * @param pageSize
     * @param username
     * @param mobile
     * @param status
     * @param storeId
     * @param type 账号类型
     * @return
     */
    Page<AdminUserVO> getPage(Integer pageNo, Integer pageSize, String username, String mobile, Integer status, Long storeId, Integer type);

    /**
     * 根据 id 获取用户信息
     * @param id
     * @return
     */
    AdminUser getAdminUserById(Integer id);

    /**
     * 根据当前用户 cookie 中的 jwt-as-token 获取当前用户信息
     * @return
     */
    AdminUserInfoVo getUserInfoByCurrentToken();

    /**
     * 更新用户信息
     * @param entity
     * @return
     */
    boolean updateAdminUser(AdminUser entity);
}
