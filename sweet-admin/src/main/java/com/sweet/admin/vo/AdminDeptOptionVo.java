package com.sweet.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "管理部门选项 VO")
public class AdminDeptOptionVo {

    @Schema(description = "部门 ID")
    private Integer id;

    @Schema(description = "部门名称")
    private String departmentName;
}
