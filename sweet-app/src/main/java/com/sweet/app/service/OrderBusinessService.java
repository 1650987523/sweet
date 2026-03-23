package com.sweet.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.app.vo.OrderDetailVo;
import com.sweet.app.vo.OrderPageVo;

/**
 * 订单业务服务接口
 */
public interface OrderBusinessService {

    /**
     * 根据订单号获取订单详情
     * @param orderNo 订单号
     * @param userId 用户 ID
     * @return 订单详情
     */
    OrderDetailVo getOrderDetail(String orderNo, Integer userId);

    /**
     * 订单分页列表
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @param orderNo 订单号（模糊查询）
     * @param userId 用户 ID
     * @param storeId 门店 ID
     * @param orderStatus 订单状态
     * @param payStatus 支付状态
     * @return 分页结果
     */
    Page<OrderPageVo> getPage(Integer pageNo, Integer pageSize, String orderNo,
                              Integer userId, Integer storeId,
                              Integer orderStatus, Integer payStatus);
}