package com.sweet.admin.vo;

import com.sweet.admin.entity.AdminUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdminUserInfoVo extends AdminUser{
    private Integer userId;
    private List<String> roles;
    private List<String> permissions;
    private List<String> buttons;
}
