package com.sweet.app.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.dev33.satoken.temp.SaTempUtil;
import com.sweet.app.dto.WechatLoginDto;
import com.sweet.service.entity.AppUser;
import com.sweet.service.service.AppUserService;
import com.sweet.app.service.AuthService;
import com.sweet.service.vo.AppUserInfoResVo;
import com.sweet.app.vo.AuthStorageVo;
import com.sweet.common.constant.AdminConstant;
import com.sweet.common.constant.AppConstant;
import com.sweet.common.enums.AppUserLevelEnum;
import com.sweet.service.properties.SweetWechatMiniProgramProperties;
import com.sweet.service.service.impl.BaseServiceImpl;
import com.sweet.service.vo.DoubleTokenResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {

    private final AppUserService appUserService;
    private final SweetWechatMiniProgramProperties wechatMiniProgramConfig;

    @Override
    public AuthStorageVo wechatLogin(WechatLoginDto dto) {
        log.info("wechatLogin dto:{}", dto);

        try {
            String code = dto.getCode();

            // 参数校验
            Assert.hasText(code, "code 不能为空");

            // 调用微信登录接口
            WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
            config.setAppid(wechatMiniProgramConfig.getAppId());
            config.setSecret(wechatMiniProgramConfig.getAppSecret());

            WxMaService wxMaService = new WxMaServiceImpl();
            wxMaService.setWxMaConfig(config);

            WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
            log.info("wechatLogin result:{}", result);
            String openid = result.getOpenid();

            // 查询用户信息（为了获取 userId）
            AppUser appUser = appUserService.getUserByOpenid(openid);
            if (ObjectUtils.isEmpty(appUser)) {
                // 新用户，先创建用户
                appUser = new AppUser();
                appUser.setWechatOpenid(openid)
                        .setNickname(AppConstant.NICKNAME)
                        .setAvatar(AppConstant.AVATAR)
                        .setLevel(AppUserLevelEnum.LEVEL_1.getCode());
                appUserService.save(appUser);
            }

            // 令牌信息
            SaLoginParameter parameter = new SaLoginParameter()
                    .setExtra(AdminConstant.USER_ID_KEY, appUser.getId());
            StpUtil.login(openid, parameter);
            String accessToken = StpUtil.getTokenValue();
            String refreshToken = SaTempUtil.createToken(openid, AppConstant.REFER_TOKEN_TIMEOUT_SECOND);
            DoubleTokenResVo doubleTokenResVo = new DoubleTokenResVo();
            doubleTokenResVo.setAccessToken(accessToken)
                    .setAccessExpiresIn(AppConstant.ACCESS_TOKEN_TIMEOUT_SECOND)
                    .setRefreshToken(refreshToken)
                    .setRefreshExpiresIn(AppConstant.REFER_TOKEN_TIMEOUT_SECOND);

            // 封装返回
            AuthStorageVo authStorageVo = new AuthStorageVo();
            authStorageVo.setTokens(doubleTokenResVo)
                    .setLoginTime(System.currentTimeMillis());

            // 用户信息
            AppUserInfoResVo userInfoResVo = new AppUserInfoResVo();
            if (ObjectUtils.isEmpty(appUser)) {
                authStorageVo.setIsNewUser(true);

                // 初始化用户信息
                appUser = new AppUser();
                appUser.setWechatOpenid(openid)
                        .setNickname(AppConstant.NICKNAME)
                        .setAvatar(AppConstant.AVATAR)
                        .setLevel(AppUserLevelEnum.LEVEL_1.getCode());
                appUserService.save(appUser);
            }
            userInfoResVo.setUserId(appUser.getId())
                    .setUsername(appUser.getUsername())
                    .setNickname(appUser.getNickname())
                    .setAvatar(appUser.getAvatar());
            authStorageVo.setUserInfo(userInfoResVo);

            return authStorageVo;
        } catch (Exception e) {
            log.error("wechatLogin error", e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
