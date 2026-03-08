package com.sweet.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweet.service.handler.PostgresqlJsonbTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@TableName(schema = "admin", value = "admin_menu")
@Schema(description = "动态菜单表（meta为JSONB类型，children通过parent_id逻辑关联）")
public class AdminMenu {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID（自增）")
    private Integer id;

    @Schema(description = "父菜单ID")
    private Integer parentId;

    @Schema(description = "路由路径")
    private String path;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "重定向地址")
    private String redirect;

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "菜单类型（C：目录 M：菜单 F：按钮）")
    private String menuType;

    @Schema(description = "菜单状态（1：正常 0：禁用）")
    private Integer menuStatus;

    @Schema(description = "是否显示（1：显示 0：隐藏）")
    private Integer visible;

    @TableField(value = "meta", typeHandler = PostgresqlJsonbTypeHandler.class)
    @Schema(description = "菜单元信息（JSON格式）")
    private Map<String, Object> meta;

    @Schema(description = "排序号")
    private Integer orderNum;

    @Schema(description = "权限标识")
    private String perms;

    @Schema(description = "关联权限ID")
    private Integer permissionId;

    @Schema(description = "是否外链（1：是 0：否）")
    private Integer isFrame;

    @Schema(description = "是否缓存（1：是 0：否）")
    private Integer isCache;

    @Schema(description = "路由参数")
    private String query;

    @Schema(description = "备注")
    private String remark;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "更新人")
    private String updateBy;
}
