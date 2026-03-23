package com.sweet.app.service;

import com.sweet.app.vo.PayCallbackVo;
import com.sweet.app.vo.PayOrderVo;

/**
 * 支付服务接口
 */
public interface PayService {

    /**
     * 处理支付回调通知
     * @param vo 回调数据
     * @return 处理结果（SUCCESS/FAIL）
     */
    String handlePayCallback(PayCallbackVo vo);

    /**
     * 获取微信支付参数
     * @param orderNo 订单号
     * @param userId 用户 ID
     * @return 支付参数
     */
    PayOrderVo getPayParams(String orderNo, Integer userId);
}
