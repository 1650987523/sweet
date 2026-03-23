package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sweet.service.dto.RefundReqVo;
import com.sweet.service.dto.RefundResVo;
import com.sweet.service.entity.OrderRefund;

import java.time.LocalDateTime;

/**
 * 订单退款服务接口
 */
public interface OrderRefundService extends IService<OrderRefund> {

    /**
     * 根据退款单号查询退款信息
     * @param refundNo 退款单号
     * @return 退款信息
     */
    OrderRefund getByRefundNo(String refundNo);

    /**
     * 根据订单号查询退款信息
     * @param orderNo 订单号
     * @return 退款信息
     */
    OrderRefund getByOrderNo(String orderNo);

    /**
     * 申请退款
     * @param req 退款请求参数
     * @return 退款响应
     */
    RefundResVo applyRefund(RefundReqVo req);

    /**
     * 分页查询退款列表
     * @param pageNo 当前页
     * @param pageSize 每页条数
     * @param refundNo 退款单号（模糊查询）
     * @param orderNo 订单号（模糊查询）
     * @param userId 用户 ID
     * @param storeId 门店 ID
     * @param refundStatus 退款状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 分页结果
     */
    Page<OrderRefund> page(Integer pageNo, Integer pageSize, String refundNo, String orderNo,
                           Integer userId, Integer storeId, Integer refundStatus,
                           LocalDateTime startTime, LocalDateTime endTime);
}
