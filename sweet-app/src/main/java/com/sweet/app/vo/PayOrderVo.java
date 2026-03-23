package com.sweet.app.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信支付订单参数响应
 */
@Data
@Accessors(chain = true)
public class PayOrderVo {

    /**
     * 小程序 AppID
     */
    private String appId;

    /**
     * 时间戳
     */
    private String timeStamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 订单详情扩展字符串（prepay_id=xxx）
     */
    @JsonProperty("package")
    private String packageValue;

    /**
     * 签名类型
     */
    private String signType;

    /**
     * 签名
     */
    private String paySign;
}