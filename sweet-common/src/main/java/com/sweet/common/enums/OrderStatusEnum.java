package com.sweet.common.enums;

/**
 * 订单状态枚举
 * <p>
 * 适用于堂食订单和到店自提订单，不涉及配送状态
 * <p>
 * 状态流转：
 * WAIT_PAY → IN_PROGRESS → COMPLETED
 *   ↓           ↓
 * CANCELLED  CANCELLED
 *    ↓
 * REFUNDING → REFUNDED
 */
public enum OrderStatusEnum {
    WAIT_PAY(0, "待支付"),        // 用户下单未支付
    IN_PROGRESS(1, "制作中"),     // 商家已接单，制作中
    COMPLETED(2, "已完成"),       // 订单完成
    CANCELLED(3, "已取消"),       // 订单已取消（支持退款）
    REFUNDING(4, "退款中"),       // 退款处理中
    REFUNDED(5, "已退款");        // 退款完成

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