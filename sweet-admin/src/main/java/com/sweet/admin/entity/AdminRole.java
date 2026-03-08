package com.sweet.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "admin", value = "admin_role")
@Schema(description = "角色表（权限集合载体，关联用户与权限的中间层）")
public class AdminRole {

    @TableId(type = IdType.AUTO)
    @Schema(description = "角色ID（自增主键）")
    private Integer id;

    @Schema(description = "角色名称（唯一，如：超级管理员、门店管理员）")
    private String roleName;

    @Schema(description = "角色编码（唯一，如：SUPER_ADMIN、STORE_ADMIN，用于代码权限判断）")
    private String roleCode;

    @Schema(description = "角色描述（说明角色的权限范围和适用人群）")
    private String description;

    @Schema(description = "角色状态（0=禁用，1=正常）")
    private Integer status = 1;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Schema(description = "创建人（用户名/ID）")
    private String createBy;

    @Schema(description = "更新人（用户名/ID）")
    private String updateBy;
}
