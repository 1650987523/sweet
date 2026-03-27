package com.sweet.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sweet.common.constant.AdminConstant;
import com.sweet.common.enums.OrderRefundStatusEnum;
import com.sweet.common.enums.OrderRefundTypeEnum;
import com.sweet.common.enums.OrderStatusEnum;
import com.sweet.common.util.OrderNoGeneratorUtil;
import com.sweet.service.dto.AdminAuditRefundDto;
import com.sweet.service.dto.ApplyRefundDto;
import com.sweet.service.dto.ApplyRefundVo;
import com.sweet.service.dto.UpdateOrderStatusDto;
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
import java.util.List;
import java.util.Objects;

/**
 * 订单退款服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundMapper, OrderRefund> implements OrderRefundService {

    private final OrderMainService orderMainService;

    @Override
    public OrderRefund getByRefundNo(String refundNo) {
        QueryWrapper<OrderRefund> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderRefund::getRefundNo, refundNo);
        return super.getOne(queryWrapper);
    }

    @Override
    public List<OrderRefund> getByOrderNo(String orderNo) {
        QueryWrapper<OrderRefund> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderRefund::getOrderNo, orderNo);
        return super.list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApplyRefundVo adminApplyRefund(ApplyRefundDto dto) {
        log.info("adminApplyRefund dto:{}", dto);

        //参数检查
        String orderNo = dto.getOrderNo();
        Integer storeId = dto.getStoreId();
        Integer refundType = dto.getRefundType();
        Long orderAmount = dto.getOrderAmount();
        Long refundAmount = dto.getRefundAmount();

        Assert.hasText(orderNo, "订单号不能为空");
        Assert.notNull(storeId, "门店 ID 不能为空");
        Assert.notNull(refundType, "退款类型不能为空");
        Assert.notNull(orderAmount, "订单金额不能为空");
        Assert.notNull(refundAmount, "退款金额不能为空");

        //订单信息,订单状态检查
        OrderMain orderMain = orderMainService.getInfo(orderNo, null);
        Assert.notNull(orderMain, "订单不存在");
        Integer orderStatus = orderMain.getOrderStatus();
        Assert.isTrue(AdminConstant.ORDER_STATUS_CAN_APPLY_REFUND.contains(orderStatus), "订单状态不允许退款");

        //是否存在申请退款记录
        //List<OrderRefund> orderRefunds = this.getByOrderNo(orderNo);

        //创建退款记录
        String refundNo = OrderNoGeneratorUtil.generateRefundNo(orderNo);
        String refundReason = dto.getRefundReason();
        String loginId = StpUtil.getLoginIdAsString();

        OrderRefund orderRefund = new OrderRefund()
                .setRefundNo(refundNo)
                .setOrderNo(orderNo)
                .setUserId(loginId)
                .setStoreId(orderMain.getStoreId())
                .setOrderAmount(orderMain.getPayAmount())
                .setRefundAmount(refundAmount)
                .setRefundType(OrderRefundTypeEnum.STORE_INITIATED.getCode())
                .setRefundReason(refundReason)
                .setRefundStatus(OrderRefundStatusEnum.PENDING_AUDIT.getCode())
                .setCreateBy(loginId);
        super.save(orderRefund);

        //更新订单为退款中
        UpdateOrderStatusDto updateDto = new UpdateOrderStatusDto();
        updateDto.setOrderNo(orderNo)
                .setOrderStatus(OrderStatusEnum.REFUNDING.getCode())
                .setReason(refundReason);
        orderMainService.updateInfoByOrderNo(updateDto);

        //结果返回
        ApplyRefundVo resVo = new ApplyRefundVo()
                .setRefundNo(refundNo)
                .setOrderNo(orderNo)
                .setRefundAmount(refundAmount)
                .setRefundStatus(OrderRefundStatusEnum.PENDING_AUDIT.getCode());

        return resVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApplyRefundVo appApplyRefund(ApplyRefundDto dto) {
        log.info("appApplyRefund dto:{}", dto);

        //参数检查
        String orderNo = dto.getOrderNo();
        Integer storeId = dto.getStoreId();
        Integer refundType = dto.getRefundType();
        Long orderAmount = dto.getOrderAmount();
        Long refundAmount = dto.getRefundAmount();

        Assert.hasText(orderNo, "订单号不能为空");
        Assert.notNull(storeId, "门店 ID 不能为空");
        Assert.notNull(refundType, "退款类型不能为空");
        Assert.notNull(orderAmount, "订单金额不能为空");
        Assert.notNull(refundAmount, "退款金额不能为空");

        //订单信息,订单归属,订单状态检查
        OrderMain orderMain = orderMainService.getInfo(orderNo, null);
        Assert.notNull(orderMain, "订单不存在");
        Long userId =  Long.valueOf(StpUtil.getExtra(AdminConstant.USER_ID_KEY).toString());
        log.info("appApplyRefund userId:{}", userId);
        Assert.notNull(userId, "用户 ID 不能为空");
        Assert.isTrue(userId.equals(Long.valueOf(orderMain.getUserId())), "订单归属用户错误");
        Integer orderStatus = orderMain.getOrderStatus();
        Assert.isTrue(AdminConstant.ORDER_STATUS_CAN_APPLY_REFUND.contains(orderStatus), "订单状态不允许退款");

        //是否存在申请退款记录
        //List<OrderRefund> orderRefunds = this.getByOrderNo(orderNo);

        //创建退款记录
        String refundNo = OrderNoGeneratorUtil.generateRefundNo(orderNo);
        String refundReason = dto.getRefundReason();
        String loginId = StpUtil.getLoginIdAsString();

        OrderRefund orderRefund = new OrderRefund()
                .setRefundNo(refundNo)
                .setOrderNo(orderNo)
                .setUserId(String.valueOf(userId))
                .setStoreId(orderMain.getStoreId())
                .setOrderAmount(orderMain.getPayAmount())
                .setRefundType(refundType)
                .setRefundReason(refundReason)
                .setRefundStatus(OrderRefundStatusEnum.PENDING_AUDIT.getCode())
                .setCreateBy(String.valueOf(userId));
        super.save(orderRefund);

        //更新订单为退款中
        UpdateOrderStatusDto updateDto = new UpdateOrderStatusDto();
        updateDto.setOrderNo(orderNo)
                .setOrderStatus(OrderStatusEnum.REFUNDING.getCode())
                .setReason(refundReason);
        orderMainService.updateInfoByOrderNo(updateDto);

        //结果返回
        ApplyRefundVo resVo = new ApplyRefundVo()
                .setRefundNo(refundNo)
                .setOrderNo(orderNo)
                .setRefundAmount(refundAmount)
                .setRefundStatus(OrderRefundStatusEnum.PENDING_AUDIT.getCode());

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

    @Override
    public Boolean adminAuditRefund(AdminAuditRefundDto dto) {
        log.info("adminAuditRefund dto:{}", dto);

        String refundNo = dto.getRefundNo();
        String orderNo = dto.getOrderNo();
        String auditReason = dto.getAuditReason();
        Integer auditStatus = dto.getAuditStatus();
        Long refundAmount = dto.getRefundAmount();

        Assert.hasText(refundNo, "退款单号不能为空");
        Assert.hasText(orderNo, "订单号不能为空");
        Assert.notNull(auditStatus, "审核状态不能为空");
        Assert.notNull(refundAmount, "退款金额不能为空");
        Assert.isTrue(StringUtils.hasText(auditReason), "审核原因不能为空");

        // 如果审核通过，设置实际退款金额；如果拒绝，则为 0
        Long actualRefundAmount = OrderRefundStatusEnum.AUDIT_APPROVED.getCode().equals(auditStatus)
                ? refundAmount
                : 0L;
        log.info("adminAuditRefund actualRefundAmount:{}", actualRefundAmount);

        UpdateWrapper<OrderRefund> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(OrderRefund::getRefundNo, refundNo)
                .set(OrderRefund::getRefundStatus, auditStatus)
                .set(OrderRefund::getAuditRemark, auditReason)
                .set(OrderRefund::getRefundAmount, actualRefundAmount)
                .set(OrderRefund::getAuditTime, LocalDateTime.now());
        super.update(updateWrapper);

        // 修改订单状态：审核通过更新为已退款，审核拒绝更新为驳回
        Integer targetOrderStatus = OrderRefundStatusEnum.AUDIT_APPROVED.getCode().equals(auditStatus)
                ? OrderStatusEnum.REFUNDED.getCode()
                : OrderStatusEnum.REJECTED.getCode();

        UpdateOrderStatusDto updateDto = new UpdateOrderStatusDto();
        updateDto.setOrderNo(orderNo)
                .setOrderStatus(targetOrderStatus);
        orderMainService.updateInfoByOrderNo(updateDto);
        return true;
    }
}
