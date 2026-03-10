package com.sweet.app.vo;

import cn.dev33.satoken.util.SaResult;
import lombok.Data;

@Data
public class LoginVo {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String avatar;
    private int status;
    private String wechatOpenid;
    private String jwtSaToken;
}
