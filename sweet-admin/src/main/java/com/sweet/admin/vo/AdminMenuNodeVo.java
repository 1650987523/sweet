package com.sweet.admin.vo;

import com.sweet.admin.entity.AdminMenu;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@Schema(description = "菜单树形结构VO")
@EqualsAndHashCode(callSuper = true) // 比较时继承父类属性
@Accessors(chain = true)
public class AdminMenuNodeVo extends AdminMenu {

    @Schema(description = "子菜单列表（树形结构关键）")
    private List<AdminMenuNodeVo> children = new ArrayList<>();

    // 可选：前端el-tree组件常用辅助字段
    @Schema(description = "是否展开节点（前端展示用）")
    private Boolean expand = false;

    @Schema(description = "是否有子节点（前端展示用）")
    private Boolean hasChildren = false;
}
