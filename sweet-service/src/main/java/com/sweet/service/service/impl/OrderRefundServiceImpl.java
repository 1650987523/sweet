package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sweet.common.enums.OrderStatusEnum;
import com.sweet.common.util.OrderNoGeneratorUtil;
import com.sweet.service.dto.RefundReqVo;
import com.sweet.service.dto.RefundResVo;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.entity.OrderRefund;
import com.sweet.service.mapper.OrderRefundMapper;
import com.sweet.service.service.OrderMainService;
import com.sweet.service.service.OrderRefundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * 订单退款服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundMapper, OrderRefund> implements OrderRefundService {

    private static final Integer DEFAULT_REFUND_TYPE = 1; // 仅退款
    private static final Integer REFUND_STATUS_PENDING = 0; // 待审核

    private final OrderMainService orderMainService;

    @Override
    public OrderRefund getByRefundNo(String refundNo) {
        return baseMapper.selectByRefundNo(refundNo);
    }

    @Override
    public OrderRefund getByOrderNo(String orderNo) {
        return baseMapper.selectByOrderNo(orderNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RefundResVo applyRefund(RefundReqVo req) {
        log.info("申请退款 req:{}", req);

        // 1. 提取请求参数
        String orderNo = req.getOrderNo();
        Integer userId = req.getUserId().intValue();

        // 2. 参数校验
        Assert.hasText(orderNo, "订单号不能为空");
        Assert.notNull(userId, "用户 ID 不能为空");

        // 3. 查询订单信息
        OrderMain orderMain = orderMainService.getInfo(orderNo, null);
        Assert.notNull(orderMain, "订单不存在");

        // 4. 校验订单归属
        Assert.isTrue(orderMain.getUserId().equals(userId), "不能申请他人的订单退款");

        // 5. 校验订单状态（只能是已支付状态）
        Assert.isTrue(OrderStatusEnum.IN_PROGRESS.getCode().equals(orderMain.getPayStatus()), "只能退款已支付的订单");

        // 6. 检查是否已申请过退款
        OrderRefund existRefund = getByOrderNo(orderNo);
        Assert.isNull(existRefund, "该订单已申请过退款");

        // 7. 使用订单实付金额作为退款金额
        Long refundAmount = orderMain.getPayAmount();

        // 8. 生成退款单号
        String refundNo = OrderNoGeneratorUtil.generateRefundNo(orderNo);

        // 9. 创建退款记录
        OrderRefund orderRefund = new OrderRefund()
                .setRefundNo(refundNo)
                .setOrderNo(orderNo)
                .setUserId(userId)
                .setStoreId(orderMain.getStoreId())
                .setOrderAmount(orderMain.getPayAmount())
                .setRefundAmount(refundAmount)
                .setActualRefundAmount(refundAmount)
                .setRefundType(DEFAULT_REFUND_TYPE)
                .setRefundReason(req.getReason())
                .setRefundStatus(REFUND_STATUS_PENDING)
                .setCreateBy(String.valueOf(userId));
        super.save(orderRefund);

        // 10. 更新订单状态为退款中
        Integer refundingCode = OrderStatusEnum.REFUNDING.getCode();
        orderMain.setPayStatus(refundingCode);
        orderMain.setOrderStatus(refundingCode);
        orderMain.setUpdateBy(String.valueOf(userId));
        orderMain.setCancelReason(req.getReason());
        orderMainService.updateById(orderMain);

        // 11. 封装返回结果
        RefundResVo resVo = new RefundResVo();
        resVo.setRefundNo(refundNo);
        resVo.setOrderNo(orderNo);
        resVo.setRefundAmount(refundAmount);
        resVo.setRefundStatus(refundingCode);

        log.info("退款申请成功：orderNo:{}, refundNo:{}, amount:{}", orderNo, refundNo, refundAmount);

        return resVo;
    }

    @Override
    public Page<OrderRefund> page(Integer pageNo, Integer pageSize, String refundNo, String orderNo,
                                  Integer userId, Integer storeId, Integer refundStatus,
                                  LocalDateTime startTime, LocalDateTime endTime) {
        Page<OrderRefund> page = new Page<>(pageNo, pageSize);

        QueryWrapper<OrderRefund> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.hasText(refundNo), OrderRefund::getRefundNo, refundNo)
                .like(StringUtils.hasText(orderNo), OrderRefund::getOrderNo, orderNo)
                .eq(Objects.nonNull(userId), OrderRefund::getUserId, userId)
                .eq(Objects.nonNull(storeId), OrderRefund::getStoreId, storeId)
                .eq(Objects.nonNull(refundStatus), OrderRefund::getRefundStatus, refundStatus)
                .ge(Objects.nonNull(startTime), OrderRefund::getCreateTime, startTime)
                .le(Objects.nonNull(endTime), OrderRefund::getCreateTime, endTime)
                .orderByDesc(OrderRefund::getCreateTime);

        return super.page(page, queryWrapper);
    }
}
