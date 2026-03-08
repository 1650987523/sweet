package com.sweet.common.enums;

/**
 * 订单类型枚举
 */
public enum OrderTypeEnum {
    SELF_PICK(1, "到店自提"),
    DELIVERY(2, "外卖配送"),
    DINE_IN(3, "堂食订单");

    private final Integer code;
    private final String desc;

    OrderTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderTypeEnum getByCode(Integer code) {
        for (OrderTypeEnum type : OrderTypeEnum.values()) {
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