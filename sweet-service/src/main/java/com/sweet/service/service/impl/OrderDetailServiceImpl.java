package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.mapper.OrderDetailMapper;
import com.sweet.service.service.OrderDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Override
    public Page<OrderDetail> getPage(Integer pageNo, Integer pageSize, String orderNo) {
        Page<OrderDetail> page = new Page<>(pageNo, pageSize);

        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.hasText(orderNo), OrderDetail::getOrderNo, orderNo);

        return super.page(page, queryWrapper);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderNo(String orderNo) {
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderDetail::getOrderNo, orderNo);
        return super.list(queryWrapper);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderNos(List<String> orderNos) {
        if(CollectionUtils.isEmpty(orderNos)){
            return List.of();
        }
        return super.list(Wrappers.<OrderDetail>lambdaQuery()
                .in(OrderDetail::getOrderNo, orderNos));
    }
}