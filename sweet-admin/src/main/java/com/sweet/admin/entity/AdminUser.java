package com.sweet.admin.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "admin", value = "admin_user")
@Schema(description = "系统用户表（权限系统核心表，存储系统操作人员信息）")
public class AdminUser {

    @TableId(type = IdType.AUTO)
    @Schema(description = "用户 ID（自增主键）")
    protected Integer id;

    @Schema(description = "登录用户名（唯一，用于系统登录）")
    protected String username;

    @Schema(description = "密码（BCrypt 加密存储，不可明文）")
    protected String password;

    @Schema(description = "联系电话（用于账号验证、通知）")
    protected String mobile;

    @Schema(description = "用户真实姓名")
    protected String realName;

    @Schema(description = "用户状态（0=禁用，1=正常）")
    protected Integer status;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    protected LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    protected LocalDateTime updateTime;

    @Schema(description = "创建人（用户名/ID）")
    protected String createBy;

    @Schema(description = "更新人（用户名/ID）")
    protected String updateBy;

    @Schema(description = "性别 1 男 2 女")
    protected Integer gender;

    @Schema(description = "所属门店 ID")
    protected Long storeId;

    @Schema(description = "账号类型（1=系统管理员，2=门店管理员）")
    protected Integer type;
}
