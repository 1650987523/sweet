package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetail>{

    /**
     * 获取订单明细分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param orderNo 订单号
     * @return 分页结果
     */
    Page<OrderDetail> getPage(Integer pageNo, Integer pageSize, String orderNo);

    /**
     * 根据订单号获取订单明细列表
     *
     * @param orderNo 订单号
     * @return 订单明细列表
     */
    List<OrderDetail> getOrderDetailsByOrderNo(String orderNo);

    /**
     * 根据订单号列表获取订单明细列表
     * @param orderNos
     * @return
     */
    List<OrderDetail> getOrderDetailsByOrderNos(List<String> orderNos);
}