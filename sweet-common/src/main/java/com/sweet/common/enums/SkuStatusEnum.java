package com.sweet.common.enums;

/**
 * SKU状态枚举
 */
public enum SkuStatusEnum {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常"),
    OUT_OF_STOCK(2, "售罄");

    private final Integer code;
    private final String desc;

    SkuStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SkuStatusEnum getByCode(Integer code) {
        for (SkuStatusEnum status : SkuStatusEnum.values()) {
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