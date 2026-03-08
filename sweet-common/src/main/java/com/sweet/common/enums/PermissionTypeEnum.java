package com.sweet.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限类型枚举
 */
@Getter
@AllArgsConstructor
public enum PermissionTypeEnum {

    MENU(1, "菜单"),
    BUTTON(2, "按钮"),
    API(3, "接口权限");

    private final Integer code;
    private final String desc;

    /**
     * 根据 code 获取枚举
     */
    public static PermissionTypeEnum getByCode(Integer code) {
        for (PermissionTypeEnum type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
