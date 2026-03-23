package com.sweet.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台登录请求 DTO")
public class LoginDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;
}
