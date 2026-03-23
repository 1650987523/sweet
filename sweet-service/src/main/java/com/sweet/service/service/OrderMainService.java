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
    Page<OrderMain> page(Integer pageNo, Integer pageSize, String orderNo,
                                        Integer userId, Integer storeId,
                                        Integer orderStatus, Integer payStatus,
                                        LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 根据 ID 获取订单信息
     *
     * @param id 订单 ID
     * @return 订单主表信息
     */
    OrderMain getInfoById(Integer id);

    /**
     * 根据订单号和用户 ID 获取订单信息
     *
     * @param orderNo 订单号
     * @param userId 用户 ID
     * @return 订单主表信息
     */
    OrderMain getInfo(String orderNo, Integer userId);

    /**
     * 根据订单号获取订单明细列表
     *
     * @param orderNo 订单号
     * @return 订单明细列表
     */
    List<OrderDetail> getOrderDetailsByOrderNo(String orderNo);

    /**
     * 更新订单状态
     *
     * @param orderNo 订单号
     * @param orderStatus 订单状态
     * @return 是否更新成功
     */
    boolean updateStatus(String orderNo, Integer orderStatus);
}