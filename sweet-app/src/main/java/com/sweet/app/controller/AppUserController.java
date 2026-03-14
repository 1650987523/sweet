package com.sweet.app.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sweet.app.dto.LoginDto;
import com.sweet.app.entity.AppUser;
import com.sweet.app.service.AppUserService;
import com.sweet.app.vo.AppUserInfoResVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/user")
@Tag(name = "用户接口")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService userService;
    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/get/currentschema")
    @Operation(summary = "获取当前数据库", description = "获取当前数据库")
    public String getCurrentSchema() {
        return jdbcTemplate.queryForObject("SELECT current_schema()", String.class);
    }

    @GetMapping()
    @Operation(summary = "分页获取用户信息", description = "分页获取用户信息")
    public ResponseEntity<IPage<AppUser>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.success(userService.page(pageNum, pageSize));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "获取用户信息", description = "根据用户id(userId)获取用户信息")
    public ResponseEntity<AppUser> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.success(userService.getById(userId));
    }

    @PostMapping()
    @Operation(summary = "注册用户", description = "注册用户")
    public ResponseEntity<AppUser> register(@RequestBody AppUser user){
        userService.save(user);
        return ResponseEntity.success(user);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "更新用户", description = "根据用户id更新用户信息")
    public ResponseEntity<Boolean> putUser(@PathVariable Long userId, @RequestBody AppUser user){
        user.setId(userId);
        return ResponseEntity.success(userService.updateById(user));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户", description = "根据用户id删除用户")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer userId){
        return ResponseEntity.success(userService.removeById(userId));
    }

    @GetMapping("/info")
    public ResponseEntity<AppUserInfoResVo> getUserInfo(){
        return ResponseEntity.success(userService.getUserByCurrentToken());
    }

    @PostMapping("/upload/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("avatar") MultipartFile file){
        return ResponseEntity.success(userService.uploadAvatar(file));
    }

    @DeleteMapping("/delete/avatar")
    public ResponseEntity<Boolean> deleteAvatar(@RequestParam String avatarUrl){
        return ResponseEntity.success(userService.deleteAvatar(avatarUrl));
    }
}
