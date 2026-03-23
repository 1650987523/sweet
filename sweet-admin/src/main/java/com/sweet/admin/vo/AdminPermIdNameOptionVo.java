package com.sweet.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理权限 ID 名称选项 VO")
public class AdminPermIdNameOptionVo {

    @Schema(description = "权限 ID")
    private Integer id;

    @Schema(description = "权限名称")
    private String permName;
}
