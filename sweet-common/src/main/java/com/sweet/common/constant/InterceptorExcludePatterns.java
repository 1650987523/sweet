package com.sweet.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器排除路径常量
 */
public class InterceptorExcludePatterns {

    /**
     * 静态资源路径（通用）
     */
    public static final List<String> STATIC_RESOURCE = List.of(
            "/js/**", "/css/**", "/images/**", "/fonts/**", "/icons/**",
            "/*.html", "/*.js", "/*.css", "/*.png", "/*.jpg", "/*.jpeg", "/*.gif", "/*.ico",
            "/swagger-ui/**", "/v3/api-docs/**"
    );

    /**
     * sweet-app 免登录路径
     */
    public static final List<String> APP_EXCLUDE = List.of(
            "/auth/wechat-login",
            "/health"
    );

    /**
     * sweet-admin 免登录路径
     */
    public static final List<String> ADMIN_EXCLUDE = List.of(
            "/user/login",
            "/health"
    );

    /**
     * 获取 sweet-app 完整排除列表（静态资源 + 免登录）
     */
    public static List<String> forApp() {
        List<String> list = new ArrayList<>(STATIC_RESOURCE);
        list.addAll(APP_EXCLUDE);
        return list;
    }

    /**
     * 获取 sweet-admin 完整排除列表（静态资源 + 免登录）
     */
    public static List<String> forAdmin() {
        List<String> list = new ArrayList<>(STATIC_RESOURCE);
        list.addAll(ADMIN_EXCLUDE);
        return list;
    }
}
