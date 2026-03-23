package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "app", value = "store")
@Schema(description = "门店实体")
public class Store {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "门店名称")
    private String name;

    @Schema(description = "门店地址")
    private String address;

    @Schema(description = "门店联系人")
    private String contactMobile;

    @Schema(description = "营业时间")
    private String businessHours;

    @Schema(description = "门店logo")
    private String logo = "";

    @Schema(description = "门店状态")
    private Integer status = 0;

    @Schema(description = "是否支持扫码点餐")
    private Boolean isSupportScan = true;

    @Schema(description = "是否支持外送")
    private Boolean isSupportTakeaway = false;

    @Schema(description = "是否支持自提")
    private Boolean isSupportSelfpick = false;

    @Schema(description = "配送范围")
    private Integer deliveryRange = 3;

    @Schema(description = "起送金额")
    private Long deliveryMinAmount = 2000L;

    @Schema(description = "配送费规则")
    private String deliveryFeeRule;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @TableField( fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
