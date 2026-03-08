package com.sweet.admin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class LoginResponseVo {

    private Integer userId;
    private String username;
    private String password;
    private Long storeId;
    private Integer type;
    private String token;
    private String refreshToken;
    private String expires;
    private List<AdminMenuNodeVo> menuTrees;
    private List<String> roles; //有些字段不符合命名规则 是为了适配前端 pure-admin开源框架
    private List<String> permissions;
}
