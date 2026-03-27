package com.sweet.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.constant.AppConstant;
import com.sweet.common.util.FileExtUtil;
import com.sweet.service.entity.AppUser;
import com.sweet.service.mapper.AppUserMapper;
import com.sweet.service.service.AppUserService;
import com.sweet.service.service.CloudflareR2Service;
import com.sweet.service.vo.AppUserInfoResVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class AppUserServiceImpl extends BaseServiceImpl<AppUserMapper, AppUser> implements AppUserService {

    private final CloudflareR2Service cloudflareR2Service;

    @Override
    public IPage<AppUser> page(Integer pageNum, Integer pageSize) {
        IPage<AppUser> page = new Page<>(pageNum, pageSize);
        return super.page(page);
    }

    @Override
    public AppUser getUserByUsername(String username) {
        QueryWrapper<AppUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AppUser::getUsername, username);
        return super.getOne(queryWrapper);
    }

    @Override
    public AppUser getUserByOpenid(String openid) {
        log.info("getUserByOpenid openid:{}", openid);

        QueryWrapper<AppUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AppUser::getWechatOpenid, openid);
        return super.getOne(queryWrapper);
    }

    @Override
    public AppUserInfoResVo getUserByCurrentToken() {
        String openid = StpUtil.getLoginIdAsString();
        log.info("getUserByCurrentToken openid:{}", openid);

        AppUser appUser = this.getUserByOpenid(openid);
        if(ObjectUtils.isEmpty(appUser)){
            throw new RuntimeException("用户不存在");
        }

        AppUserInfoResVo appUserInfoResVo = new AppUserInfoResVo();
        appUserInfoResVo.setUserId(appUser.getId())
                .setUsername(appUser.getUsername())
                .setNickname(appUser.getNickname())
                .setAvatar(appUser.getAvatar())
                .setLevel(appUser.getLevel());
        return appUserInfoResVo;
    }

    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            // 参数校验
            Assert.notNull(file, "头像文件不能为空");

            // 获取当前登录用户的 ID 作为路径标识
            String userId = StpUtil.getLoginIdAsString();

            // 上传
            Map<String, InputStream> imageKeyToInputStream = new HashMap<>();
            String key = String.format("%s/%s-%s.%s",
                    AppConstant.OSS_USER_AVATAR_PREFIX,
                    userId,
                    System.currentTimeMillis(),
                    FileExtUtil.getExtension(file.getOriginalFilename()));
            imageKeyToInputStream.put(key, file.getInputStream());
            cloudflareR2Service.putBatchObjects(imageKeyToInputStream);

            // 返回上传后的 URL
            return cloudflareR2Service.generateUploadUrl(key);
        } catch (Exception e) {
            log.error("uploadAvatar failed", e);
            throw new RuntimeException("上传头像失败：" + e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteAvatar(String avatarUrl) {
        if (ObjectUtils.isEmpty(avatarUrl)) {
            return true;
        }

        cloudflareR2Service.deleteObject(avatarUrl);
        return true;
    }
}
