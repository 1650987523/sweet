package com.sweet.app.Interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.sweet.common.constant.AdminConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * 权限拦截器（sweet-app 专用）
 * 验证 app 用户是否登录
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录校验
        Object loginId = StpUtil.getLoginIdDefaultNull();
        log.info("loginId: {}", loginId);
        if (Objects.isNull(loginId)) {
            response.setStatus(401);
            return false;
        }


        //权限校验
        Integer userId = (Integer) StpUtil.getExtra(AdminConstant.USER_ID_KEY);
        log.info("AuthInterceptor: userId:{}, openid:{}", userId, loginId);

        //获取用户的权限列表,理论上只要前端有界面都是存在权限的,只是关于用户数据的获取要使用userId去查
        return true;
    }
}
