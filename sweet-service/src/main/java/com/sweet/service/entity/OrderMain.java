package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "app", value = "order_main")
public class OrderMain {

    @TableId(type = IdType.AUTO)
    @Schema(description = "订单ID（自增主键）", example = "6001", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "订单编号（唯一标识，如：ORD2025122210300001）", example = "ORD2025122210300001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderNo;

    @Schema(description = "用户ID（下单用户）", example = "10001", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;

    @Schema(description = "门店ID（下单门店）", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer storeId;

    @Schema(description = "顾客姓名", example = "张三")
    private String customerName;

    @Schema(description = "联系电话", example = "13800138000", requiredMode = Schema.RequiredMode.REQUIRED, pattern = "^1[3-9]\\d{9}$")
    private String customerPhone;

    @Schema(description = "订单总金额（单位：分）", example = "3500", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long totalAmount;

    @Schema(description = "商品金额（单位：分）", example = "3000", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long goodsAmount;

    @Schema(description = "配送费（单位：分）", example = "500", defaultValue = "0")
    private Long deliveryFee = 0L;

    @Schema(description = "优惠金额（单位：分）", example = "0", defaultValue = "0")
    private Long discountAmount = 0L;

    @Schema(description = "实付金额（单位：分）", example = "3500", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long payAmount;

    @Schema(description = "支付方式（0=未选择，1=微信支付，2=支付宝支付，3=现金支付）", example = "1")
    private Integer payType;

    @Schema(description = "支付状态（0=未支付，1=已支付，2=支付失败，3=退款中，4=已退款）", example = "1", defaultValue = "0")
    private Integer payStatus = 0;

    @Schema(description = "订单状态（0=待支付，1=待接单，2=待配送，3=已完成，4=已取消，5=退款中）", example = "3", defaultValue = "0")
    private Integer orderStatus = 0;

    @Schema(description = "订单类型（1=到店自提，2=外卖配送，3=堂食订单）", example = "1", defaultValue = "1")
    private Integer orderType = 1;

    @Schema(description = "取餐/配送地址（自提时可为门店地址，配送时为用户地址）", example = "北京市海淀区中关村大街19号新中关B1层")
    private String deliveryAddr;

    @Schema(description = "预计取餐/送达时间", example = "2025-12-22T11:00:00")
    private LocalDateTime takeTime;

    @Schema(description = "门店名称（冗余字段，避免关联查询）", example = "XX奶茶旗舰店（海淀店）")
    private String storeName;

    @Schema(description = "使用的优惠券ID（多个用逗号分隔）", example = "COUPON20251201001")
    private String couponId;

    @Schema(description = "订单备注（如：少冰、打包）", example = "少冰，打包带走")
    private String remark;

    @Schema(description = "取消原因（仅订单状态为已取消时有效）", example = "用户临时取消订单")
    private String cancelReason;

    @Schema(description = "创建时间", example = "2025-12-22T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2025-12-22T11:00:00", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "创建人（用户名/ID，系统自动填充）", example = "system", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新人（用户名/ID，系统自动填充）", example = "system", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}
