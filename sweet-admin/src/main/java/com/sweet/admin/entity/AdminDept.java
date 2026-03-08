package com.sweet.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(schema = "admin", value = "admin_dept")
@Schema(description = "部门表（支持多级部门层级，存储组织架构信息）")
public class AdminDept {

    @TableId(type = IdType.AUTO)
    @Schema(description = "部门ID（自增主键）")
    private Integer id;

    @Schema(description = "父部门ID（关联本表id，一级部门为空字符串）")
    private Integer parentId;

    @Schema(description = "部门名称（如：厦门总公司、研发部）")
    private String departmentName;

    @Schema(description = "部门状态（0=禁用，1=正常）")
    private Integer status;

    @Schema(description = "部门备注信息")
    private String remark;

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