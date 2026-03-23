package com.sweet.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "管理后台登录响应 VO")
public class LoginResponseVo {

    @Schema(description = "用户 ID")
    private Integer userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "门店 ID")
    private Long storeId;

    @Schema(description = "用户类型")
    private Integer type;

    @Schema(description = "访问令牌")
    private String token;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "过期时间")
    private String expires;

    @Schema(description = "菜单树")
    private List<AdminMenuNodeVo> menuTrees;

    @Schema(description = "角色列表")
    private List<String> roles;

    @Schema(description = "权限列表")
    private List<String> permissions;
}
