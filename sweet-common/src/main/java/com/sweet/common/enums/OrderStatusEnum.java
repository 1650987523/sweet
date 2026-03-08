package com.sweet.common.enums;

/**
 * 订单状态枚举
 */
public enum OrderStatusEnum {
    WAIT_PAY(0, "待支付"),
    WAIT_ACCEPT(1, "待接单"),
    WAIT_DELIVERY(2, "待配送"),
    COMPLETED(3, "已完成"),
    CANCELLED(4, "已取消"),
    REFUNDING(5, "退款中");

    private final Integer code;
    private final String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderStatusEnum getByCode(Integer code) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
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