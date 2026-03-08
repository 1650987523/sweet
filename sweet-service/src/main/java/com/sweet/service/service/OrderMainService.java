package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMainService extends BaseService<OrderMain>{

    /**
     * 多条件查询订单分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param orderNo 订单号（模糊查询）
     * @param userId 用户 ID
     * @param storeId 门店 ID
     * @param orderStatus 订单状态
     * @param payStatus 支付状态
     * @param startTime 开始时间（创建时间）
     * @param endTime 结束时间（创建时间）
     * @return 分页结果
     */
    Page<OrderMain> getPageByCondition(Integer pageNo, Integer pageSize, String orderNo,
                                        Integer userId, Integer storeId,
                                        Integer orderStatus, Integer payStatus,
                                        LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 获取订单详情（包含订单主表信息和明细列表）
     *
     * @param id 订单 ID
     * @return 订单主表信息（明细列表需通过 getOrderDetailsByOrderNo 单独查询）
     */
    OrderMain getOrderDetailById(Integer id);

    /**
     * 根据订单号获取订单详情（包含订单主表信息和明细列表）
     *
     * @param orderNo 订单号
     * @return 订单主表信息（明细列表需通过 getOrderDetailsByOrderNo 单独查询）
     */
    OrderMain getOrderDetailByOrderNo(String orderNo);

    /**
     * 根据订单号获取订单明细列表
     *
     * @param orderNo 订单号
     * @return 订单明细列表
     */
    List<OrderDetail> getOrderDetailsByOrderNo(String orderNo);
}