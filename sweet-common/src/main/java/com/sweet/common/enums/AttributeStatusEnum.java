package com.sweet.common.enums;

/**
 * 属性状态枚举
 */
public enum AttributeStatusEnum {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常");

    private final Integer code;
    private final String desc;

    AttributeStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AttributeStatusEnum getByCode(Integer code) {
        for (AttributeStatusEnum status : AttributeStatusEnum.values()) {
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