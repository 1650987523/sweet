import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.BCrypt;

public class SweetTest {
    public static void main(String[] args) {
        String wanglaoban = SaSecureUtil.aesEncrypt("wanglaoban", "123456");
        String wanglaoban1 = SaSecureUtil.aesDecrypt("wanglaoban", "VEWVV7p3at4sE/4kc0IAoA==");
        String hashpw = BCrypt.hashpw("123456");
        System.out.println(wanglaoban);
        System.out.println(wanglaoban1);
        System.out.println(hashpw);

        boolean checkpw = BCrypt.checkpw("123456", "$2a$10$HzJ2jsBRmf4yM1IH9iZLw.XEhZ1lWPnZngYHFxt.aZ93xGguVVtHW");
        System.out.println(checkpw);

//        {
//            "username": "zhangsan",
//                "password": "VEWVV7p3at4sE/4kc0IAoA=="
//        }
    }
}
