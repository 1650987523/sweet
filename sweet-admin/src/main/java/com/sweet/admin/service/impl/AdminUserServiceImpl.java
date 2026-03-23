package com.sweet.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sweet.admin.dto.LoginDto;
import com.sweet.admin.entity.*;
import com.sweet.admin.mapper.AdminUserMapper;
import com.sweet.admin.service.*;
import com.sweet.admin.vo.AdminMenuNodeVo;
import com.sweet.admin.vo.AdminRoleOptionVo;
import com.sweet.admin.vo.AdminUserVO;
import com.sweet.admin.vo.AdminUserInfoVo;
import com.sweet.admin.vo.LoginResponseVo;
import com.sweet.common.constant.AdminConstant;
import com.sweet.common.util.DateFormatUtil;
import com.sweet.security.utils.LoginPassWordUtil;
import com.sweet.service.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    private final LoginPassWordUtil loginPassWordUtil;
    private final AdminRoleService adminRoleService;
    private final AdminMenuService adminMenuService;
    private final AdminUserRoleService adminUserRoleService;
    private final AdminRolePermissionService adminRolePermissionService;
    private final ObjectMapper objectMapper;

    @Override
    public LoginResponseVo login(LoginDto dto) {
        log.info("AdminUserService login dto:{}", dto);

        String username = dto.getUsername();
        String enCodePassWord = dto.getPassword();

        //根据 username 获取用户信息
        AdminUser adminUser = this.getUserByUsername(username);
        Assert.notNull(adminUser, "用户不存在");
        String dbPassWord = adminUser.getPassword();
        Integer userId = adminUser.getId();

        //密码比对
        boolean ret = loginPassWordUtil.checkPassWordByEncodePassWord(enCodePassWord, dbPassWord);
        Assert.isTrue(ret, "密码错误");

        //登录并生成 jwt token
        SaLoginParameter saLoginParameter = new SaLoginParameter()
                .setExtra(AdminConstant.USER_NAME_KEY, username)
                .setExtra(AdminConstant.USER_ID_KEY, adminUser.getId());
        StpUtil.login(adminUser.getId(), saLoginParameter);
        String tokenValue = StpUtil.getTokenValue();

        //角色列表
        List<Integer> adminRoleIds = adminUserRoleService.getRoleIdsByUserId(userId);
        List<String> roleCodes = adminRoleService.getRoleCodesByRoleIds(adminRoleIds);

        //权限列表
        List<String> permCodes = adminRolePermissionService.getPermCodesByRoleIds(adminRoleIds);

        //菜单树
        List<AdminMenuNodeVo> menuTrees = adminMenuService.getAdminMenuTreeByPerms(permCodes);

        //返回信息
        LoginResponseVo loginResponseVo = new LoginResponseVo();
        loginResponseVo.setUserId(userId).setUsername(username)
                .setStoreId(adminUser.getStoreId())
                .setType(adminUser.getType())
                .setToken(tokenValue)
                .setRefreshToken(tokenValue)
                .setRoles(roleCodes)
                .setPermissions(permCodes)
                .setMenuTrees(menuTrees)
                .setExpires(DateFormatUtil.offsetSecond(StpUtil.getTokenTimeout(), AdminConstant.DATE_PATTERN_HORIZONTAL_LINE));
        return loginResponseVo;
    }

    @Override
    public AdminUser getUserByUsername(String username) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUser::getUsername, username);
        return super.getOne(queryWrapper);
    }

    @Override
    public JsonNode refreshToken(String userId, String username) {
        log.info("AdminUserService refreshToken userId:{}, username:{}", userId, username);

        //获取旧 token
        String accessToken = StpUtil.getTokenValue();

        //生成新 token
        StpUtil.login(userId, new SaLoginParameter().setExtra("username", username));
        String refreshToken = StpUtil.getTokenValue();


        //获取 token 过期时间
        String expires = DateFormatUtil.offsetSecond(StpUtil.getTokenTimeout(), AdminConstant.DATE_PATTERN_HORIZONTAL_LINE);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("access", accessToken);
        objectNode.put("refresh", refreshToken);
        objectNode.put("expires", expires);
        return objectNode;
    }

    @Override
    public Page<AdminUserVO> getPage(Integer pageNo, Integer pageSize, String username, String mobile, Integer status, Long storeId, Integer type) {
        Page<AdminUser> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.hasText(username), AdminUser::getUsername, username)
                .like(StringUtils.hasText(mobile), AdminUser::getMobile, mobile)
                .eq(Objects.nonNull(status), AdminUser::getStatus, status)
                .eq(Objects.nonNull(storeId), AdminUser::getStoreId, storeId)
                .eq(Objects.nonNull(type), AdminUser::getType, type);
        Page<AdminUser> userPage = super.page(page, queryWrapper);

        // 转换为 VO 并填充角色信息
        List<AdminUserVO> userVOList = userPage.getRecords().stream()
                .map(this::convertToVO)
                .toList();

        Page<AdminUserVO> voPage = new Page<>(pageNo, pageSize);
        voPage.setTotal(userPage.getTotal());
        voPage.setRecords(userVOList);
        return voPage;
    }

    /**
     * 转换为 AdminUserVO 并填充角色信息
     * @param user 用户实体
     * @return AdminUserVO
     */
    private AdminUserVO convertToVO(AdminUser user) {
        AdminUserVO vo = new AdminUserVO();
        BeanUtils.copyProperties(user, vo);
        // 获取用户角色列表
        List<AdminRoleOptionVo> roles = adminUserRoleService.getRoleOptionsByUserId(user.getId());
        vo.setRoles(roles);
        return vo;
    }

    @Override
    public AdminUser getAdminUserById(Integer id) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AdminUser::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    public AdminUserInfoVo getUserInfoByCurrentToken() {
        int userId = StpUtil.getLoginIdAsInt();

        //获取用户信息
        AdminUser adminUser = this.getAdminUserById(userId);

        //获取用户对应的角色列表
        List<Integer> adminRoleIds = adminUserRoleService.getRoleIdsByUserId(userId);
        List<String> roleCodes = adminRoleService.getRoleCodesByRoleIds(adminRoleIds);

        //获取用户权限列表
        List<String> permCodes = adminRolePermissionService.getPermCodesByRoleIds(adminRoleIds);

        AdminUserInfoVo adminUserInfoVo =  new AdminUserInfoVo();
        BeanUtils.copyProperties(adminUser, adminUserInfoVo);
        adminUserInfoVo.setUserId(userId)
                .setRoles(roleCodes)
                .setPermissions(permCodes)
                .setButtons(List.of());

        return adminUserInfoVo;

    }

    @Override
    public boolean save(AdminUser entity) {
        log.info("save AdminUser:{}", entity);

        //校验必须传入的字段
        String username = entity.getUsername();
        String password = entity.getPassword();
        Assert.hasText(username, "账号不能为空");
        Assert.hasText(password, "密码不能为空");

        //判断用户是否存在
        AdminUser adminUser = this.getUserByUsername(username);
        if(!ObjectUtils.isEmpty(adminUser)){
            throw new RuntimeException("账号已存在");
        }

        //用户不存在则新增，开始加密密码
        String decodePassWord = loginPassWordUtil.decodePassWord(password);
        log.info("save decodePassWord:{}", decodePassWord);
        String bcryptPassWord = loginPassWordUtil.bcrypt(decodePassWord);
        log.info("save bcryptPassWord:{}", bcryptPassWord);
        entity.setPassword(bcryptPassWord);

        //设置默认账号类型（如果不传则默认为普通用户账号）
        if (entity.getType() == null) {
            entity.setType(2); // 默认为普通用户账号
        }

        //保存
        return super.save(entity);
    }

    @Override
    public boolean updateAdminUser(AdminUser entity) {
        log.info("updateAdminUser:{}", entity);

        Assert.notNull(entity.getId(), "用户 ID 不能为空");

        //获取数据库中的用户信息
        AdminUser dbUser = this.getAdminUserById(entity.getId());
        Assert.notNull(dbUser, "用户不存在");

        //如果密码被修改，则加密密码
        if (StringUtils.hasText(entity.getPassword())) {
            String decodePassWord = loginPassWordUtil.decodePassWord(entity.getPassword());
            String bcryptPassWord = loginPassWordUtil.bcrypt(decodePassWord);
            entity.setPassword(bcryptPassWord);
        } else {
            //密码为空则不修改密码，使用数据库中的密码
            entity.setPassword(dbUser.getPassword());
        }

        return super.updateById(entity);
    }
}
