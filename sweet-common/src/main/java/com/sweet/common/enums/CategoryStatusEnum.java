package com.sweet.common.enums;

/**
 * 分类状态枚举
 */
public enum CategoryStatusEnum {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer code;
    private final String desc;

    CategoryStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryStatusEnum getByCode(Integer code) {
        for (CategoryStatusEnum status : CategoryStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}