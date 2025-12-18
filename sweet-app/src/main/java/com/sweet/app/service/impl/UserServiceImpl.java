package com.sweet.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.app.dto.LoginDto;
import com.sweet.app.mapper.UserMapper;
import com.sweet.app.entity.User;
import com.sweet.app.service.UserService;
import com.sweet.app.vo.LoginVo;
import com.sweet.service.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> page(Integer pageNum, Integer pageSize) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        return super.page(page, null);
    }

    @Override
    public LoginVo loginByDouyin(LoginDto dto) {
        return new LoginVo();
    }
}
