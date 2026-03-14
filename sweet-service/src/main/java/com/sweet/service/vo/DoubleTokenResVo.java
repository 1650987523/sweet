package com.sweet.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DoubleTokenResVo {

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "访问令牌有效期(秒)")
    private Long accessExpiresIn;

    @Schema(description = "刷新令牌有效期(秒)")
    private Long refreshExpiresIn;
}
