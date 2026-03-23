package com.sweet.security.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求日志拦截器
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "request-start-time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(START_TIME, System.currentTimeMillis());

        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================== Start ==========================================\n");
        sb.append("URL            : ").append(request.getRequestURL()).append("\n");
        sb.append("Method         : ").append(request.getMethod()).append("\n");
        sb.append("IP             : ").append(getClientIp(request)).append("\n");

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            sb.append("Controller     : ").append(handlerMethod.getBeanType().getName()).append("\n");
            sb.append("Method         : ").append(handlerMethod.getMethod().getName()).append("\n");
        }

        sb.append("Headers        : ").append(getHeaders(request)).append("\n");
        String queryString = request.getQueryString();
        if (queryString != null) {
            sb.append("Query String   : ").append(queryString).append("\n");
        }
        sb.append("===========================================================================================");

        log.info(sb.toString());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        long duration = (startTime != null) ? (endTime - startTime) : 0;

        StringBuilder sb = new StringBuilder();
        sb.append("\n=========================================== End ===========================================\n");
        sb.append("URL            : ").append(request.getRequestURL()).append("\n");
        sb.append("Duration       : ").append(duration).append(" ms\n");
        sb.append("Status         : ").append(response.getStatus()).append("\n");
        if (ex != null) {
            sb.append("Exception      : ").append(ex.getMessage()).append("\n");
        }
        sb.append("===========================================================================================");

        log.info(sb.toString());
    }

    private String getHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        return headerMap.toString();
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 15 && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }
}
