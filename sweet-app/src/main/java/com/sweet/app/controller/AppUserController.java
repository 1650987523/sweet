package com.sweet.app.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sweet.app.dto.LoginDto;
import com.sweet.app.service.UserService;
import com.sweet.app.entity.User;
import com.sweet.app.vo.LoginVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Tag(name = "用户接口")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/login/douyin")
    @Operation(summary = "用户登录(通过抖音)", description = "用户登录(通过抖音)")
    public ResponseEntity<LoginVo> loginByDouyin(@RequestBody LoginDto dto) {
        return ResponseEntity.success(userService.loginByDouyin(dto));
    }

    @PostMapping("/login/password")
    @Operation(summary = "用户登录(通过密码)", description = "用户登录(通过密码)")
    public ResponseEntity<LoginVo> loginByPassword(@RequestBody LoginDto dto) {
        return ResponseEntity.success(userService.loginByPassword(dto));
    }

    @GetMapping("/is-login")
    @Operation(summary = "判断用户是否登录", description = "判断用户是否登录")
    public ResponseEntity<SaResult> isLogin() {
        return ResponseEntity.success(SaResult.ok("用户已登录"));
    }

    @PutMapping("/logout")
    @Operation(summary = "用户登出", description = "用户登出")
    public ResponseEntity<SaResult> logout() {
        return ResponseEntity.success(SaResult.ok("用户已登出"));
    }

    @GetMapping("/get/currentschema")
    @Operation(summary = "获取当前数据库", description = "获取当前数据库")
    public String getCurrentSchema() {
        return jdbcTemplate.queryForObject("SELECT current_schema()", String.class);
    }

    @GetMapping()
    @Operation(summary = "分页获取用户信息", description = "分页获取用户信息")
    public ResponseEntity<IPage<User>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.success(userService.page(pageNum, pageSize));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "获取用户信息", description = "根据用户id(userId)获取用户信息")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.success(userService.getById(userId));
    }

    @PostMapping()
    @Operation(summary = "注册用户", description = "注册用户")
    public ResponseEntity<User> register(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.success(user);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "更新用户", description = "根据用户id更新用户信息")
    public ResponseEntity<Boolean> putUser(@PathVariable Integer userId, @RequestBody User user){
        user.setId(userId);
        return ResponseEntity.success(userService.updateById(user));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户", description = "根据用户id删除用户")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer userId){
        return ResponseEntity.success(userService.removeById(userId));
    }
}
