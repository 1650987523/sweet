package com.sweet.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sweet.admin.service.OrderBusinessService;
import com.sweet.admin.vo.OrderDetailVo;
import com.sweet.service.entity.AppUser;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.service.AppUserService;
import com.sweet.service.service.OrderDetailService;
import com.sweet.service.service.OrderMainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private final OrderMainService orderMainService;
    private final OrderDetailService orderDetailService;
    private final AppUserService appUserService;

    @Override
    public OrderDetailVo getDetailVoByOrderNo(String orderNo) {
        log.info("Getting order detail vo by order no: {}", orderNo);

        OrderMain orderMain = orderMainService.getInfo(orderNo, null);
        if(Objects.isNull(orderMain)){
            throw new RuntimeException("订单不存在");
        }

        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderNo(orderNo);

        // 查询用户信息
        AppUser userInfo = null;
        if (Objects.nonNull(orderMain.getUserId())) {
            userInfo = appUserService.getById(orderMain.getUserId());
        }

        return new OrderDetailVo()
                .setOrderMain(orderMain)
                .setOrderDetails(orderDetails)
                .setUserInfo(userInfo);
    }

    @Override
    public boolean deleteOrderByOrderNo(String orderNo) {
        log.info("Deleting order by order no: {}", orderNo);

        // 删除订单明细
        orderDetailService.remove(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getOrderNo, orderNo));

        // 删除订单主表
        return orderMainService.remove(Wrappers.<OrderMain>lambdaQuery().eq(OrderMain::getOrderNo, orderNo));
    }
}