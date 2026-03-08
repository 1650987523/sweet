package com.sweet.common.enums;

/**
 * 支付状态枚举
 */
public enum PayStatusEnum {
    NOT_PAID(0, "未支付"),
    PAID(1, "已支付"),
    PAY_FAILED(2, "支付失败"),
    REFUNDING(3, "退款中"),
    REFUNDED(4, "已退款");

    private final Integer code;
    private final String desc;

    PayStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PayStatusEnum getByCode(Integer code) {
        for (PayStatusEnum status : PayStatusEnum.values()) {
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