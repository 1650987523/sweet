package com.sweet.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 微信支付回调通知 VO
 * 参考：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_5.shtml
 */
@Data
@Schema(description = "微信支付回调通知")
public class PayCallbackVo {

    @Schema(description = "回调通知 ID")
    private String id;

    @Schema(description = "通知创建时间")
    private String createTime;

    @Schema(description = "资源类型")
    private String resourceType;

    @Schema(description = "通知类型")
    private String eventType;

    @Schema(description = "回调摘要")
    private String summary;

    @Schema(description = "加密数据资源")
    private WechatResource resource;

    @Schema(description = "支付金额(适配前端假支付)")
    private Integer amount;

    @Schema(description = "商户订单号(适配前端假支付)")
    private String outTradeNo;

    @Schema(description = "交易状态(适配前端假支付)")
    private String tradeState;

    @Schema(description = "微信订单号(适配前端假支付)")
    private String transactionId;

    @Data
    @Schema(description = "微信支付加密资源")
    public static class WechatResource {

        @Schema(description = "原始数据类型")
        private String originalType;

        @Schema(description = "加密算法")
        private String algorithm;

        @Schema(description = "密文数据（Base64 编码）")
        private String ciphertext;

        @Schema(description = "附加数据（Base64 编码）")
        private String associatedData;

        @Schema(description = "随机串（Base64 编码）")
        private String nonce;
    }
}