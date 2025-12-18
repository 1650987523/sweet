package com.sweet.app.vo;

import lombok.Data;

@Data
public class LoginVo {
    private String id;
    private String username;
    private String nickname;
    private String email;
    private String avatar;
    private String douyinOpenId;
    private int status;
    private String token;
}
