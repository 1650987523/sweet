package com.sweet.app.controller;

import com.sweet.app.dto.WechatLoginDto;
import com.sweet.app.service.AuthService;
import com.sweet.app.vo.AuthStorageVo;
import com.sweet.common.response.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "授权接口")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "微信登录授权")
    @PostMapping("/wechat-login")
    public ResponseEntity<AuthStorageVo> wechatLogin(@RequestBody WechatLoginDto dto) {
        return ResponseEntity.success(authService.wechatLogin(dto));
    }

}
