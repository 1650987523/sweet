package com.sweet.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "admin", value = "admin_user_role")
@Schema(description = "用户-角色关联表（实现用户与角色的多对多关系）")
public class AdminUserRole {

    @TableId(type = IdType.AUTO)
    @Schema(description = "关联ID（自增主键）", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "用户ID（关联admin_user表主键）", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;

    @Schema(description = "角色ID（关联admin_role表主键）", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer roleId;

    @Schema(description = "创建时间", example = "2025-12-22T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "创建人（用户名/ID）", example = "system")
    private String createBy;
}
