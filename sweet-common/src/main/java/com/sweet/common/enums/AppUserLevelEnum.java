package com.sweet.common.enums;

/**
 * 应用用户等级枚举
 */
public enum AppUserLevelEnum {

    /** 普通用户 */
    LEVEL_1(1, "普通用户"),

    /** 黄金用户 */
    LEVEL_2(2, "黄金用户"),

    /** 铂金用户 */
    LEVEL_3(3, "铂金用户");

    private final int code;
    private final String desc;

    AppUserLevelEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}