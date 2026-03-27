package com.sweet.admin.service;

import com.sweet.admin.vo.OrderDetailVo;

public interface OrderBusinessService {

    /**
     * 根据订单号获取订单详情 VO
     *
     * @param orderNo 订单号
     * @return 订单详情 VO（包含订单主表和明细列表）
     */
    OrderDetailVo getDetailVoByOrderNo(String orderNo);

    /**
     * 根据订单号删除订单
     * @param orderNo
     * @return
     */
    boolean deleteOrderByOrderNo(String orderNo);
}