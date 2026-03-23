package com.sweet.app.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.sweet.common.annotation.AutoFillUserId;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 自动填充 userId 切面
 */
@Slf4j
@Aspect
@Component
public class UserIdFillAspect {

    @Pointcut("@annotation(com.sweet.common.annotation.AutoFillUserId)")
    public void autoFillUserIdPointcut() {
    }

    @Before("autoFillUserIdPointcut()")
    public void before(JoinPoint joinPoint) {
        // 获取当前登录用户 ID
        Integer userId = StpUtil.getLoginIdAsInt();
        log.info("AutoFillUserId: userId={}", userId);

        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取注解
        AutoFillUserId annotation = method.getAnnotation(AutoFillUserId.class);
        if (annotation == null) {
            return;
        }

        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();

        // 遍历参数，找到名为 query 的参数并设置 userId
        for (int i = 0; i < args.length; i++) {
            if ("query".equals(paramNames[i]) && args[i] != null) {
                try {
                    // 使用反射设置 userId 字段
                    var field = args[i].getClass().getDeclaredField("userId");
                    field.setAccessible(true);

                    // 只有当 userId 为 null 时才填充
                    Integer currentUserId = (Integer) field.get(args[i]);
                    if (currentUserId == null) {
                        field.set(args[i], userId);
                        log.info("AutoFillUserId: 已填充 query.userId={}", userId);
                    }
                } catch (Exception e) {
                    log.warn("AutoFillUserId: 无法填充 userId, 原因：{}", e.getMessage());
                }
                break;
            }
        }
    }
}
