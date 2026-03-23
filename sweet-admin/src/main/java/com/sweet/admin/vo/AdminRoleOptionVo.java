package com.sweet.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "管理角色选项 VO")
public class AdminRoleOptionVo {

    @Schema(description = "角色 ID")
    private Integer id;

    @Schema(description = "角色名称")
    private String roleName;
}
