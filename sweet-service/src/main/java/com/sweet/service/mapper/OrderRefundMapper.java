package com.sweet.service.mapper;

import com.sweet.service.entity.OrderRefund;
import org.apache.ibatis.annotations.Param;

/**
 * 订单退款 Mapper 接口
 */
public interface OrderRefundMapper extends BaseMapper<OrderRefund> {

    /**
     * 根据退款单号查询退款信息
     * @param refundNo 退款单号
     * @return 退款信息
     */
    OrderRefund selectByRefundNo(@Param("refundNo") String refundNo);

    /**
     * 根据订单号查询退款信息
     * @param orderNo 订单号
     * @return 退款信息
     */
    OrderRefund selectByOrderNo(@Param("orderNo") String orderNo);
}
