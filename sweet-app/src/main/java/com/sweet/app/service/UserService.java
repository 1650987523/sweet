package com.sweet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sweet.app.dto.LoginDto;
import com.sweet.app.entity.User;
import com.sweet.app.vo.LoginVo;
import com.sweet.service.service.BaseService;

public interface UserService extends BaseService<User> {

    IPage<User> page(Integer pageNum, Integer pageSize);

    LoginVo loginByDouyin(LoginDto dto);
}
