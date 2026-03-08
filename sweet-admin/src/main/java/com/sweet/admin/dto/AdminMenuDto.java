package com.sweet.admin.dto;

import com.sweet.admin.entity.AdminMenu;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdminMenuDto extends AdminMenu {

    //private List<AdminMenuDto> children;

}
