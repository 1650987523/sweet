package com.sweet.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public Page<OrderMain> page(Integer pageNo, Integer pageSize, String orderNo,
                                Integer userId, Integer storeId,
                                Integer orderStatus, Integer payStatus,
                                LocalDateTime startTime, LocalDateTime endTime) {
        if(Objects.isNull(userId)){
            userId = StpUtil.getLoginIdAsInt();
        }
        if(Objects.nonNull(storeId) && storeId.equals(0)){
            storeId = null;
        }

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
        queryWrapper.lambda().orderByDesc(OrderMain::getCreateTime);

        return super.page(page, queryWrapper);
    }

    @Override
    public OrderMain getInfoById(Integer id) {
        return super.getById(id);
    }

    @Override
    public OrderMain getInfo(String orderNo, Integer userId) {
        QueryWrapper<OrderMain> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StringUtils.hasText(orderNo), OrderMain::getOrderNo, orderNo)
                .eq(Objects.nonNull(userId), OrderMain::getUserId, userId);
        return super.getOne(queryWrapper);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderNo(String orderNo) {
        return orderDetailService.getOrderDetailsByOrderNo(orderNo);
    }

    @Override
    public boolean updateStatus(String orderNo, Integer orderStatus) {
        UpdateWrapper<OrderMain> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(OrderMain::getOrderNo, orderNo).set(OrderMain::getOrderStatus, orderStatus);
        return super.update(updateWrapper);
    }
}