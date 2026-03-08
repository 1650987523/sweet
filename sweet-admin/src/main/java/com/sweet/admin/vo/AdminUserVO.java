package com.sweet.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "用户信息 VO（包含角色信息）")
public class AdminUserVO {

    @Schema(description = "用户 ID")
    private Integer id;

    @Schema(description = "登录用户名")
    private String username;

    @Schema(description = "联系电话")
    private String mobile;

    @Schema(description = "用户真实姓名")
    private String realName;

    @Schema(description = "用户状态")
    private Integer status;

    @Schema(description = "性别 1 男 2 女")
    private Integer gender;

    @Schema(description = "所属门店 ID")
    private Long storeId;

    @Schema(description = "账号类型 1-管理员 2-普通用户")
    private Integer type;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Schema(description = "用户角色列表")
    private List<AdminRoleOptionVo> roles;
}