package com.sweet.app.vo;

import lombok.Data;

@Data
public class LoginResultVo {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String avatar;
    private int status;
    private String wechatOpenid;
    private String jwtSaToken;
}
