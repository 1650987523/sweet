package com.sweet.common.enums;

/**
 * 订单退款状态枚举
 */
public enum OrderRefundStatusEnum {
    PENDING_AUDIT(0, "待审核"),
    AUDIT_APPROVED(1, "审核通过"),
    AUDIT_REJECTED(2, "审核拒绝"),
    REFUND_SUCCESS(3, "退款成功"),
    REFUND_FAILED(4, "退款失败");

    private final Integer code;
    private final String desc;

    OrderRefundStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderRefundStatusEnum getByCode(Integer code) {
        for (OrderRefundStatusEnum status : OrderRefundStatusEnum.values()) {
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
