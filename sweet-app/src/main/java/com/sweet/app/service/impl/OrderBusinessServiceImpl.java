package com.sweet.app.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.app.mapper.OrderBusinessMapper;
import com.sweet.app.service.OrderBusinessService;
import com.sweet.app.vo.OrderDetailVo;
import com.sweet.app.vo.OrderPageVo;
import com.sweet.common.constant.AdminConstant;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.service.OrderDetailService;
import com.sweet.service.service.OrderMainService;
import com.sweet.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 订单业务服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private final OrderMainService orderMainService;
    private final ProductService productService;
    private final OrderDetailService orderDetailService;

    @Override
    public OrderDetailVo getOrderDetail(String orderNo, Integer userId) {

        //拦截器校验必须登录了 所以这里不会出现空指针
        if(Objects.isNull(userId)){
            userId = Integer.valueOf(StpUtil.getExtra(AdminConstant.USER_ID_KEY).toString());
        }
        log.info("getOrderDetail orderNo:{}, userId:{}", orderNo, userId);

        // 查询订单主表信息（同时校验订单归属）
        OrderMain orderMain = orderMainService.getInfo(orderNo, userId.intValue());
        Assert.notNull(orderMain, "订单不存在");

        // 查询订单明细列表
        List<OrderDetail> orderDetails = orderMainService.getOrderDetailsByOrderNo(orderNo);

        // 4. 封装返回结果
        OrderDetailVo resVo = new OrderDetailVo();
        resVo.setOrderMain(orderMain);
        resVo.setOrderDetails(orderDetails);

        log.info("getOrderDetail success orderNo:{}", orderNo);

        return resVo;
    }

    @Override
    public Page<OrderPageVo> getPage(Integer pageNo, Integer pageSize, String orderNo,
                                  Integer userId, Integer storeId,
                                  Integer orderStatus, Integer payStatus) {

        // 自动填充 userId
        if (Objects.isNull(userId)) {
            userId = StpUtil.getLoginIdAsInt();
        }

        // storeId = 0 时不作为查询条件
        if (Objects.nonNull(storeId) && storeId.equals(0)) {
            storeId = null;
        }
        log.info("getPage orderNo:{}, userId:{}, storeId:{}, orderStatus:{}, payStatus:{}",
                orderNo, userId, storeId, orderStatus, payStatus);

        //最终返回
        Page<OrderPageVo> voPage = new Page<>(pageNo, pageSize);
        List<OrderPageVo> orderPageVos = new ArrayList<>();

        //分页查询订单主表
        Page<OrderMain> page = orderMainService.page(pageNo, pageSize, orderNo, userId, storeId, orderStatus,
                payStatus, null, null);

        List<OrderMain> orderMains = page.getRecords();
        if(CollectionUtils.isEmpty(orderMains)){
            return voPage;
        }

        //收集订单号列表
        List<String> orderNos = new ArrayList<>();
        for (OrderMain orderMain : orderMains) {
            orderNos.add(orderMain.getOrderNo());
        }

        // 根据订单号列表查询订单明细列表
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderNos(orderNos);
        for (OrderMain orderMain : orderMains) {

            OrderPageVo orderPageVo = new OrderPageVo();
            orderPageVo.setOrderMain(orderMain);
            orderPageVo.setOrderDetails(new ArrayList<>());

            for (OrderDetail orderDetail : orderDetails) {
                if(orderDetail.getOrderNo().equals(orderMain.getOrderNo())){
                    orderPageVo.getOrderDetails().add(orderDetail);
                }
            }

            orderPageVos.add(orderPageVo);
        }

        voPage.setRecords(orderPageVos).setCurrent(page.getCurrent()).setTotal(page.getTotal());
        return voPage;
    }
}