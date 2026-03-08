package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@TableName(schema = "app", value = "table_qrcode")
@Accessors(chain = true)
public class TableQrcode {

    @TableId(type = IdType.AUTO)
    @Schema(description = "桌码ID（自增主键）")
    protected Integer id;

    @Schema(description = "关联门店ID")
    protected Integer storeId;

    @Schema(description = "桌号名称")
    protected String qrcodeName;

    @Schema(description = "桌码编号（唯一标识）")
    protected String qrcodeNo;

    @Schema(description = "桌码图片(二维码图片)")
    protected String qrcodeUrl;

    @Schema(description = "桌码关联内容")
    protected String qrcodeContent;

    @Schema(description = "桌码状态（0=禁用，1=正常，2=维护中）")
    protected Integer status = 0;

    @Schema(description = "是否VIP桌")
    protected Boolean isVip = false;

    @Schema(description = "最大容纳人数")
    protected Integer maxPeople = 4;

    @Schema(description = "所属区域（如：大厅、包厢1、靠窗区）")
    protected String area;

    @Schema(description = "备注信息（如：靠窗无烟桌）")
    protected String remark;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;
}
