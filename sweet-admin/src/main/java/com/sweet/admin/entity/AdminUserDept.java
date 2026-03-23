package com.sweet.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName(schema = "admin", value = "admin_user_dept")
@Schema(description = "管理部门用户关联实体")
public class AdminUserDept {

    @TableId(type = IdType.AUTO)
    @Schema(name = "主键")
    private Integer id;

    @Schema(name = "用户ID")
    private Integer userId;

    @Schema(name = "部门ID")
    private Integer deptId;

    @Schema(name = "是否主部门")
    private Integer isMain;

    @Schema(name = "创建时间")
    @DateTimeFormat(pattern = "YYYY-")
    private LocalDateTime createTime;

    @Schema(name = "创建者")
    private String createBy;
}
