package com.sweet.common.enums;

/**
 * 微信支付交易状态枚举
 */
public enum PayTradeStatusEnum {
    SUCCESS("SUCCESS", "支付成功"),
    FAILURE("FAILURE", "支付失败"),
    REFUND("REFUND", "转入退款"),
    NOTPAY("NOTPAY", "未支付"),
    CLOSED("CLOSED", "已关闭");

    private final String code;
    private final String desc;

    PayTradeStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PayTradeStatusEnum getByCode(String code) {
        for (PayTradeStatusEnum status : PayTradeStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
