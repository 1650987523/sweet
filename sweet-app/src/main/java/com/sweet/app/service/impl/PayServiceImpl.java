package com.sweet.app.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.sweet.app.service.PayService;
import com.sweet.app.vo.PayCallbackVo;
import com.sweet.app.vo.PayOrderVo;
import com.sweet.common.constant.AdminConstant;
import com.sweet.common.enums.OrderStatusEnum;
import com.sweet.common.enums.PayTradeStatusEnum;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.properties.SweetWechatMiniProgramProperties;
import com.sweet.service.service.OrderMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * 支付服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final OrderMainService orderMainService;
    private final SweetWechatMiniProgramProperties wechatMiniProgramProperties;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String handlePayCallback(PayCallbackVo vo) {
        log.info("处理支付回调 vo:{}", vo);

        // 1. 从 body 中获取参数
        String orderNo = vo.getOutTradeNo();
        String transactionId = vo.getTransactionId();
        String tradeState = vo.getTradeState();
        Integer amount = vo.getAmount();

        // 2. 参数校验
        Assert.hasText(orderNo, "订单号不能为空");
        Assert.notNull(amount, "支付金额不能为空");

        // 3. 查询订单信息
        OrderMain orderMain = orderMainService.getInfo(orderNo, null);
        Assert.notNull(orderMain, "订单不存在");

        // 4. 校验订单状态（只能是待支付状态）
        Assert.isTrue(OrderStatusEnum.WAIT_PAY.getCode().equals(orderMain.getPayStatus()), "订单已支付，无需重复处理");

        // 5. 校验支付金额（防止篡改）
        //Assert.isTrue(orderMain.getPayAmount().equals(amount.longValue()), "支付金额不匹配");

        // 6. 只处理支付成功的回调
        if (!PayTradeStatusEnum.SUCCESS.getCode().equals(tradeState)) {
            return tradeState;
        }

        // 7. 更新订单状态
        orderMain.setPayStatus(OrderStatusEnum.IN_PROGRESS.getCode()); // 已支付，待接单
        orderMain.setOrderStatus(OrderStatusEnum.IN_PROGRESS.getCode()); // 待接单
        orderMain.setUpdateBy("system");
        orderMainService.updateById(orderMain);

        log.info("支付回调处理成功：orderNo:{}, transactionId:{}, amount:{}", orderNo, transactionId, amount);

        // 8. 返回成功响应（微信要求的格式）
        return PayTradeStatusEnum.SUCCESS.getCode();
    }

    @Override
    public PayOrderVo getPayParams(String orderNo, Integer userId) {
        if(Objects.isNull(userId)){
            userId = (Integer) StpUtil.getExtra(AdminConstant.USER_ID_KEY);
        }

        log.info("获取支付参数 orderNo:{}, userId:{}", orderNo, userId);

        // 2. 查询订单信息
        OrderMain orderMain = orderMainService.getInfo(orderNo, userId);
        Assert.notNull(orderMain, "订单不存在");

        // 3. 校验订单状态（只能是待支付状态）
        Assert.isTrue(OrderStatusEnum.WAIT_PAY.getCode().equals(orderMain.getPayStatus()), "订单已支付，无需重复支付");

        // TODO: 后续接入真实支付时，调用微信统一下单接口获取 prepay_id
        // WxPayUnifiedOrderV3Request request = ...
        // WxPayUnifiedOrderV3Result result = wxPayService.createOrderV3("JSAPI", request);

        // 4. 返回支付参数（模拟数据）
        PayOrderVo resVo = new PayOrderVo();
        resVo.setAppId(wechatMiniProgramProperties.getAppId())
                .setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000))
                .setNonceStr(UUID.randomUUID().toString().replace("-", ""))
                .setPackageValue("prepay_id=mock_" + System.currentTimeMillis())
                .setSignType("RSA")
                .setPaySign("mock_pay_sign");

        log.info("获取支付参数成功：orderNo:{}", orderNo);

        return resVo;
    }
}
