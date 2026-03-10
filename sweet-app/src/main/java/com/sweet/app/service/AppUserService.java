package com.sweet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sweet.app.dto.LoginDto;
import com.sweet.app.entity.AppUser;
import com.sweet.service.service.BaseService;

public interface AppUserService extends BaseService<AppUser> {

    IPage<AppUser> page(Integer pageNum, Integer pageSize);

    AppUser getUserByUsername(String username);
}
