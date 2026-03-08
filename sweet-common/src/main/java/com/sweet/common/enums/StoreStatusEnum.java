package com.sweet.common.enums;

/**
 * 门店状态枚举
 */
public enum StoreStatusEnum {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer code;
    private final String desc;

    StoreStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StoreStatusEnum getByCode(Integer code) {
        for (StoreStatusEnum status : StoreStatusEnum.values()) {
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