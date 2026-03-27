package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "order_refund")
@Schema(description = "订单退款实体")
public class OrderRefund {

    @TableId(type = IdType.AUTO)
    @Schema(description = "退款 ID（自增主键）")
    private Long id;

    @Schema(description = "退款单号")
    private String refundNo;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "用户 ID")
    private String userId;

    @Schema(description = "门店 ID")
    private Integer storeId;

    @Schema(description = "订单总金额（单位：分）")
    private Long orderAmount;

    @Schema(description = "申请退款金额（单位：分）")
    private Long refundAmount;

    @Schema(description = "实际退款金额（单位：分）")
    private Long actualRefundAmount;

    @Schema(description = "退款类型（1=门店发起退款，2=用户退款）")
    private Integer refundType;

    @Schema(description = "退款原因")
    private String refundReason;

    @Schema(description = "退款说明/备注")
    private String refundDesc;

    @Schema(description = "退款凭证图片（JSON 数组）")
    @TableField(typeHandler = com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler.class)
    private java.util.List<String> refundImages;

    @Schema(description = "退款状态（0=待审核，1=审核通过，2=审核拒绝，3=退款成功，4=退款失败）")
    private Integer refundStatus;

    @Schema(description = "审核人 ID")
    private Integer auditUserId;

    @Schema(description = "审核人姓名")
    private String auditUserName;

    @Schema(description = "审核时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注/拒绝原因")
    private String auditRemark;

    @Schema(description = "退款成功时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime refundSuccessTime;

    @Schema(description = "退款失败原因")
    private String refundFailReason;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}
