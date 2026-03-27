package com.sweet.common.util;

/**
 * 订单号生成工具类
 */
public class OrderNoGeneratorUtil {

    private static final String ORDER_NO_PREFIX = "";
    private static final String REFUND_NO_PREFIX = "REF";

    /**
     * 生成订单号
     * 格式：storeId-userId-tableNo-timestamp(毫秒)
     */
    public static String generateOrderNo(Long storeId, Long userId, String tableNo) {
        long timestamp = System.currentTimeMillis();
        return ORDER_NO_PREFIX + storeId + "-" + userId + "-" + tableNo + "-" + timestamp;
    }

    /**
     * 生成退款单号
     * 格式：REF + orderNo
     */
    public static String generateRefundNo(String orderNo) {
        return REFUND_NO_PREFIX  + "-" + orderNo;
    }
}
