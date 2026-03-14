package com.sweet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sweet.app.entity.AppUser;
import com.sweet.app.vo.AppUserInfoResVo;
import com.sweet.service.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

public interface AppUserService extends BaseService<AppUser> {

    /**
     * 分页查询用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<AppUser> page(Integer pageNum, Integer pageSize);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    AppUser getUserByUsername(String username);

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    AppUser getUserByOpenid(String openid);

    /**
     * 根据当前登录的令牌查询用户信息
     * @return
     */
    AppUserInfoResVo getUserByCurrentToken();

    /**
     * 上传用户头像
     * @param file 头像文件
     * @return 上传后的头像 URL
     */
    String uploadAvatar(MultipartFile file);

    /**
     * 删除用户头像
     * @param avatarUrl 头像 URL
     * @return 是否删除成功
     */
    Boolean deleteAvatar(String avatarUrl);
}
