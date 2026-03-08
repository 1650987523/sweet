package com.sweet.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "admin", value = "admin_permission")
@Schema(description = "权限表（权限最小单元，存储菜单、按钮、接口级权限）")
public class AdminPermission {

    @TableId(type = IdType.AUTO)
    @Schema(description = "菜单ID（自增主键）")
    private Integer id;

    @Schema(description = "权限名称")
    private String permName;

    @Schema(description = "权限编码")
    private String permCode;

    @Schema(description = "权限类型")
    private Integer permType;

    @Schema(description = "权限URL")
    private String url;

    @Schema(description = "权限方法")
    private String method;

    @Schema(description = "父级权限ID")
    private Integer parentId;

    @Schema(description = "权限展示排序")
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "更新人")
    private String updateBy;

}
