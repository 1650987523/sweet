package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sweet.service.dto.AdminAuditRefundDto;
import com.sweet.service.dto.ApplyRefundDto;
import com.sweet.service.dto.ApplyRefundVo;
import com.sweet.service.entity.OrderRefund;

import java.time.LocalDateTime;
import java.util.List;

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
    List<OrderRefund> getByOrderNo(String orderNo);

    /**
     * 门店后台申请退款
     * @param dto 退款请求参数
     * @return 退款响应
     */
    ApplyRefundVo adminApplyRefund(ApplyRefundDto dto);

    /**
     * app端用户申请退款
     * @param dto
     * @return
     */
    ApplyRefundVo appApplyRefund(ApplyRefundDto dto);

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

    /**
     * 门店后台审核退款
     * @param dto 审核请求参数
     * @return 是否审核成功
     */
    Boolean adminAuditRefund(AdminAuditRefundDto dto);
}
