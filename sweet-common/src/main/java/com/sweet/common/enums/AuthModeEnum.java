package com.sweet.common.enums;

/**
 * 认证模式枚举
 */
public enum AuthModeEnum {

    /** 单 Token 模式 */
    SINGLE("single"),

    /** 双 Token 模式 */
    DOUBLE("double");

    private final String value;

    AuthModeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}