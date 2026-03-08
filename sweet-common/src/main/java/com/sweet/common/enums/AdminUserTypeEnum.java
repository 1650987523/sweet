package com.sweet.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 管理员账号类型枚举
 */
@Getter
@AllArgsConstructor
public enum AdminUserTypeEnum {

    ADMIN(1, "管理员账号"),
    USER(2, "普通用户账号");

    private final Integer code;
    private final String desc;

    /**
     * 根据 code 获取枚举
     */
    public static AdminUserTypeEnum getByCode(Integer code) {
        for (AdminUserTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}