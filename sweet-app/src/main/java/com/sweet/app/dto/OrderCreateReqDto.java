package com.sweet.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 订单创建请求 DTO（扫码点餐）
 */
@Data
@Schema(description = "订单创建请求 DTO（扫码点餐）")
public class OrderCreateReqDto {

    @Schema(description = "店铺 ID")
    private Long storeId;

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "桌台号")
    private String tableNo;

    @Schema(description = "支付方式（1=微信支付，2=支付宝支付，3=现金支付）")
    private Integer payType;

    @Schema(description = "使用的优惠券 ID（多个用逗号分隔）")
    private String couponId;

    @Schema(description = "优惠金额（单位：分）")
    private Long discountAmount = 0L;

    @Schema(description = "订单备注（如：少冰、打包带走）")
    private String remark;

    @Schema(description = "商品列表")
    private List<SkuItem> skus;

    @Data
    @Schema(description = "商品 SKU 项")
    public static class SkuItem {

        @Schema(description = "商品 ID")
        private Long productId;

        @Schema(description = "SKU ID")
        private Long skuId;

        @Schema(description = "购买数量")
        private Integer count;

        @Schema(description = "商品单价（单位：分）")
        private Long price;
    }
}
