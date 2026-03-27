package com.sweet.common.enums;

/**
 * 订单退款类型枚举
 */
public enum OrderRefundTypeEnum {
    STORE_INITIATED(1, "门店发起退款"),
    USER_INITIATED(2, "用户退款");

    private final Integer code;
    private final String desc;

    OrderRefundTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderRefundTypeEnum getByCode(Integer code) {
        for (OrderRefundTypeEnum type : OrderRefundTypeEnum.values()) {
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
