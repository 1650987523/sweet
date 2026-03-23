package com.sweet.app.service;

import com.sweet.app.dto.WechatLoginDto;
import com.sweet.app.vo.AuthStorageVo;
import com.sweet.service.vo.DoubleTokenResVo;
import com.sweet.service.service.BaseService;

public interface AuthService extends BaseService{


    /**
     * 微信登录
     * @param dto
     * @return
     */
    AuthStorageVo wechatLogin(WechatLoginDto dto);
}
