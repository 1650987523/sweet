package com.sweet.service.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "用户信息响应 VO")
public class AppUserInfoResVo {

    @Schema(description = "用户 ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户名称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "用户等级")
    private Integer level;
}
