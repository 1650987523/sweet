package com.sweet.app.vo;

import com.sweet.common.enums.AuthModeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthStorageVo {

    @Schema(description = "登录模式 双token还是单token")
    private String mode = AuthModeEnum.DOUBLE.getValue();

    @Schema(description = "令牌信息")
    private com.sweet.service.vo.DoubleTokenResVo tokens;  // SingleTokenRes 或 DoubleTokenRes

    @Schema(description = "用户信息")
    private AppUserInfoResVo userInfo;

    @Schema(description = "登录时间戳（毫秒）")
    private Long loginTime;

    @Schema(description = "是否为新用户")
    private Boolean isNewUser = false;
}
