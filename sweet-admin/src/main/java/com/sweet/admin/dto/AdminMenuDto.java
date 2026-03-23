package com.sweet.admin.dto;

import com.sweet.admin.entity.AdminMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "管理后台菜单 DTO")
public class AdminMenuDto extends AdminMenu {

    //private List<AdminMenuDto> children;

}
