package com.sweet.admin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminDeptOptionVo {

    private Integer id;
    private String departmentName;
}
