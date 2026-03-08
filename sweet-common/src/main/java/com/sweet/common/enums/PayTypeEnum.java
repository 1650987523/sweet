package com.sweet.common.enums;

/**
 * 支付方式枚举
 */
public enum PayTypeEnum {
    NOT_SELECTED(0, "未选择"),
    WECHAT_PAY(1, "微信支付"),
    ALIPAY(2, "支付宝支付"),
    CASH(3, "现金支付");

    private final Integer code;
    private final String desc;

    PayTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PayTypeEnum getByCode(Integer code) {
        for (PayTypeEnum type : PayTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
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