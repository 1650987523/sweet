package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "order_main")
public class OrderMain {

    @TableId(type = IdType.AUTO)
    @Schema(description = "订单 ID（自增主键）")
    private Integer id;

    @Schema(description = "订单编号（唯一标识）")
    private String orderNo;

    @Schema(description = "用户 ID（下单用户）")
    private Integer userId;

    @Schema(description = "门店 ID（下单门店）")
    private Integer storeId;

    @Schema(description = "顾客姓名")
    private String customerName;

    @Schema(description = "联系电话")
    private String customerPhone;

    @Schema(description = "订单总金额（单位：分）")
    private Long totalAmount;

    @Schema(description = "商品金额（单位：分）")
    private Long goodsAmount;

    @Schema(description = "配送费（单位：分）")
    private Long deliveryFee = 0L;

    @Schema(description = "优惠金额（单位：分）")
    private Long discountAmount = 0L;

    @Schema(description = "实付金额（单位：分）")
    private Long payAmount;

    @Schema(description = "支付方式（0=未选择，1=微信支付，2=支付宝支付，3=现金支付）")
    private Integer payType;

    @Schema(description = "支付状态（0=未支付，1=已支付，2=支付失败，3=退款中，4=已退款）")
    private Integer payStatus = 0;

    @Schema(description = "订单状态（0=待支付，1=待接单，2=待配送，3=已完成，4=已取消，5=退款中）")
    private Integer orderStatus = 0;

    @Schema(description = "订单类型（1=到店自提，2=外卖配送，3=堂食订单）")
    private Integer orderType = 1;

    @Schema(description = "取餐/配送地址")
    private String deliveryAddr;

    @Schema(description = "预计取餐/送达时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime takeTime;

    @Schema(description = "门店名称（冗余字段，避免关联查询）")
    private String storeName;

    @Schema(description = "使用的优惠券 ID（多个用逗号分隔）")
    private String couponId;

    @Schema(description = "订单备注")
    private String remark;

    @Schema(description = "取消原因（仅订单状态为已取消时有效）")
    private String cancelReason;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Schema(description = "创建人（用户名/ID，系统自动填充）")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新人（用户名/ID，系统自动填充）")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @Schema(description = "桌号")
    private String tableNo;
}
