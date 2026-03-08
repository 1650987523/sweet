package com.sweet.admin.Interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.sweet.admin.entity.AdminPermission;
import com.sweet.admin.service.AdminPermissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口权限拦截器
 */
@Slf4j
@Component
@AllArgsConstructor
public class ApiPermissionInterceptor implements HandlerInterceptor {

    private AdminPermissionService permissionService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    // 缓存：method:url -> permCode
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        // 初始化缓存
        if (cache.isEmpty()) {
            loadCache();
        }

        // 匹配权限
        for (var entry : cache.entrySet()) {
            String[] parts = entry.getKey().split(":", 2);
            String cacheMethod = parts[0];
            String cacheUrl = parts[1];

            // 方法匹配且路径匹配
            if (matchMethod(method, cacheMethod) && pathMatcher.match(cacheUrl, path)) {
                String permCode = entry.getValue();
                log.debug("权限校验：{} {} -> {}", method, path, permCode);
                StpUtil.checkPermission(permCode);
                return true;
            }
        }

        // 未配置权限要求，直接放行
        return true;
    }

    /**
     * 加载权限缓存
     */
    private synchronized void loadCache() {
        if (!cache.isEmpty()) return;

        List<AdminPermission> permissions = permissionService.getApiPermissions();
        for (AdminPermission p : permissions) {
            if (p.getUrl() != null && p.getPermCode() != null) {
                String key = (p.getMethod() != null ? p.getMethod() : "*") + ":" + p.getUrl();
                cache.put(key, p.getPermCode());
            }
        }
        log.info("权限缓存加载完成，共 {} 条", cache.size());
    }

    /**
     * 方法匹配：* 可匹配所有
     */
    private boolean matchMethod(String requestMethod, String cacheMethod) {
        return "*".equals(cacheMethod) || requestMethod.equals(cacheMethod);
    }
}
