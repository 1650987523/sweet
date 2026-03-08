package com.sweet.security.utils;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.symmetric.AES;
import com.sweet.security.config.SecurityPropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class LoginPassWordUtil {

    private final SecurityPropertiesConfig securityPropertiesConfig;

    public String decodePassWord(String encodedPassword) {
        AES aes = SecureUtil.aes(securityPropertiesConfig.getWebAesSecret().getBytes());
        return aes.decryptStr(encodedPassword);
        //return SaSecureUtil.aesDecrypt(securityPropertiesConfig.getWebAesSecret(), encodedPassword);
    }

    public String bcrypt(String decodePassWord) {
        String hashPassWord = BCrypt.hashpw(decodePassWord);
        return hashPassWord;
    }

    public boolean matches(String decodePassWord, String dbPassWord) {
        log.info("PasswordService decodePassWord:{}, dbPassWord:{}", decodePassWord, dbPassWord);
        return BCrypt.checkpw(decodePassWord, dbPassWord);
    }

    public boolean checkPassWordByEncodePassWord(String encodePassWord, String dbPassWord) {
        String decodePassWord = this.decodePassWord(encodePassWord);
        return this.checkPassWordByDecodePassWord(decodePassWord, dbPassWord);
    }

    public boolean checkPassWordByDecodePassWord(String decodePassWord, String dbPassWord) {
        return this.matches(decodePassWord, dbPassWord);
    }
}
