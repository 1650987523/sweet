package com.sweet.admin.vo;

import com.sweet.admin.entity.AdminDept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "部门树形结构VO")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminDeptNodeVo extends AdminDept {

    @Schema(description = "子部门列表（树形结构关键）")
    private List<AdminDeptNodeVo> children = new ArrayList<>();

    @Schema(description = "是否展开节点（前端展示用）")
    private Boolean expand = false;

    @Schema(description = "是否有子节点（前端展示用）")
    private Boolean hasChildren = false;
}
