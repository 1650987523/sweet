package com.sweet.app.service;

import com.sweet.app.dto.WechatLoginDto;
import com.sweet.app.vo.LoginResultVo;
import com.sweet.service.service.BaseService;

public interface AuthService extends BaseService{


    /**
     * 微信登录
     * @param dto
     * @return
     */
    LoginResultVo wechatLogin(WechatLoginDto dto);

    /**
     *  获取微信信息
     * @param code
     */
    void code2Session(String code);
}
