package com.sweet.app.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.app.dto.LoginDto;
import com.sweet.app.mapper.UserMapper;
import com.sweet.app.entity.User;
import com.sweet.app.service.UserService;
import com.sweet.app.vo.LoginVo;
import com.sweet.security.utils.LoginPassWordUtil;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    private final LoginPassWordUtil loginPassWordUtil;

    @Override
    public IPage<User> page(Integer pageNum, Integer pageSize) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        return super.page(page);
    }

    @Override
    public LoginVo loginByDouyin(LoginDto dto) {
        return new LoginVo();
    }

    @Override
    public LoginVo loginByPassword(LoginDto dto) {
        log.info("loginByPassword dto:{}", dto);

        String username = dto.getUsername();
        String password = dto.getPassword();

        Assert.hasText(username, "用户名不能为空");
        Assert.hasText(password, "密码不能为空");

        //根据用户名查询密码
        User user = this.getUserByUsername(username);
        String dbPassWord = user.getPassword();

        //密码对比
        boolean ret = loginPassWordUtil.checkPassWordByEncodePassWord(password, dbPassWord);
        if(!ret){
            throw new RuntimeException("密码错误");
        }

        //登录
        StpUtil.login(user.getId(), new SaLoginParameter()
                .setExtra("username", user.getUsername())
                .setExtra("nickname", user.getNickname())
                .setExtra("email", user.getEmail()));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        //返回用户信息
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(user, loginVo);
        loginVo.setJwtSaToken(tokenInfo.getTokenValue());
        return loginVo;
    }

    @Override
    public User getUserByUsername(@NonNull String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        return super.getOne(queryWrapper);
    }
}
