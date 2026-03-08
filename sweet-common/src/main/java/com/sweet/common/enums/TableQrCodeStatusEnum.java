package com.sweet.common.enums;

/**
 * 桌码状态枚举
 */
public enum TableQrCodeStatusEnum {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常"),
    MAINTENANCE(2, "维护中");

    private final Integer code;
    private final String desc;

    TableQrCodeStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TableQrCodeStatusEnum getByCode(Integer code) {
        for (TableQrCodeStatusEnum status : TableQrCodeStatusEnum.values()) {
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