package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "双 Token 响应 VO")
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
