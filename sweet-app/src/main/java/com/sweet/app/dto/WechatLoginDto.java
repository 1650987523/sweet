package com.sweet.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "微信小程序登录请求 DTO")
public class WechatLoginDto {

    @Schema(description = "微信登录 code")
    private String code;
}
