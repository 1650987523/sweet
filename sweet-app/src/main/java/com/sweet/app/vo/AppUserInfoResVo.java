package com.sweet.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AppUserInfoResVo {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户名称")
    private String nickname;

    @Schema(description = "昵称")
    private String avatar;

    @Schema(description = "用户等级")
    private Integer level;
}
