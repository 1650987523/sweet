package com.sweet.app.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.sweet.app.dto.WechatLoginDto;
import com.sweet.app.properties.WechatMiniProgramProperties;
import com.sweet.app.service.AuthService;
import com.sweet.app.vo.LoginResultVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {

    private final WebClient webClient;
    private final WechatMiniProgramProperties wechatMiniProgramProperties;


    @Override
    public LoginResultVo wechatLogin(WechatLoginDto dto) {
        log.info("wechatLogin dto:{}", dto);

        try {

            String code = dto.getCode();

            //参数校验
            Assert.hasText(code, "code不能为空");

            //调用微信登录接口
            WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
            config.setAppid(wechatMiniProgramProperties.getAppId());
            config.setSecret(wechatMiniProgramProperties.getAppSecret());

            WxMaService wxMaService = new WxMaServiceImpl();
            wxMaService.setWxMaConfig(config);

            WxMaJscode2SessionResult result = wxMaService.getUserService().getSessionInfo(code);
            log.info("wechatLogin result:{}", result);
        }catch (Exception e){
            log.error("wechatLogin error", e);
        }

        return null;
    }

    @Override
    public void code2Session(String code) {
        log.info("code2Session code:{}", code);

        Disposable subscribe = webClient.get()
                .uri(wechatMiniProgramProperties.getCode2session())
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> log.info("code2Session response:{}", response));
        log.info("code2Session subscribe:{}", subscribe);

    }
}
