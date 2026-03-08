package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.mapper.OrderMainMapper;
import com.sweet.service.service.OrderDetailService;
import com.sweet.service.service.OrderMainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class OrderMainServiceImpl extends BaseServiceImpl<OrderMainMapper, OrderMain> implements OrderMainService {

    private final OrderDetailService orderDetailService;

    @Override
    public Page<OrderMain> getPageByCondition(Integer pageNo, Integer pageSize, String orderNo,
                                               Integer userId, Integer storeId,
                                               Integer orderStatus, Integer payStatus,
                                               LocalDateTime startTime, LocalDateTime endTime) {
        Page<OrderMain> page = new Page<>(pageNo, pageSize);

        QueryWrapper<OrderMain> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.hasText(orderNo), OrderMain::getOrderNo, orderNo)
                .eq(Objects.nonNull(userId), OrderMain::getUserId, userId)
                .eq(Objects.nonNull(storeId), OrderMain::getStoreId, storeId)
                .eq(Objects.nonNull(orderStatus), OrderMain::getOrderStatus, orderStatus)
                .eq(Objects.nonNull(payStatus), OrderMain::getPayStatus, payStatus)
                .ge(Objects.nonNull(startTime), OrderMain::getCreateTime, startTime)
                .le(Objects.nonNull(endTime), OrderMain::getCreateTime, endTime);

        return super.page(page, queryWrapper);
    }

    @Override
    public OrderMain getOrderDetailById(Integer id) {
        // 获取订单主表信息
        return super.getById(id);
    }

    @Override
    public OrderMain getOrderDetailByOrderNo(String orderNo) {
        // 获取订单主表信息
        QueryWrapper<OrderMain> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderMain::getOrderNo, orderNo);
        return super.getOne(queryWrapper);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderNo(String orderNo) {
        return orderDetailService.getOrderDetailsByOrderNo(orderNo);
    }
}