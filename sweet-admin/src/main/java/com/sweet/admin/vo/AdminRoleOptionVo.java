package com.sweet.admin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminRoleOptionVo {

    private Integer id;
    private String roleName;
}
