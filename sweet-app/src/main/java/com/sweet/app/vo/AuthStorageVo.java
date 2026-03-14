package com.sweet.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthStorage {

    @Schema(description = "登录模式 双token还是单token")
    private AuthMode mode;
    private DoubleTokenResVo tokens;  // SingleTokenRes 或 DoubleTokenRes
    private UserInfoResVo userInfo;
    private Long loginTime;  // 登录时间戳（毫秒）
}
