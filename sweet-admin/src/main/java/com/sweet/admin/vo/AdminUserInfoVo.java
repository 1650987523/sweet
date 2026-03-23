package com.sweet.admin.vo;

import com.sweet.admin.entity.AdminUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "管理后台用户信息 VO")
public class AdminUserInfoVo extends AdminUser{

    @Schema(description = "用户 ID")
    private Integer userId;

    @Schema(description = "角色列表")
    private List<String> roles;

    @Schema(description = "权限列表")
    private List<String> permissions;

    @Schema(description = "按钮权限列表")
    private List<String> buttons;
}
