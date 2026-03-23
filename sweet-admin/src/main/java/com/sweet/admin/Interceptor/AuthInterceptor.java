package com.sweet.admin.Interceptor;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * 权限拦截器（sweet-admin 专用）
 * 验证管理员是否有操作权限
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

        //权限校验 1.根据用户获取角色列表 2.根据角色获取权限列表 3.根据路由/接口/按钮请求解析权限
        //long userId = (long) loginId;

        return true;
    }
}
