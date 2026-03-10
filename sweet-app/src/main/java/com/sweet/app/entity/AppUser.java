package com.sweet.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("app_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String mobile;

    private int status;

    private String avatar;

    @TableField(exist = false)
    private String douyinOpenId;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

}
