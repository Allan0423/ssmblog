package cn.zp.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * 敏感信息加密工具类
 */
public class CryptoUtil {

    private static final String salt = "ssmblog";
    public static String sha256WithSalt(String message){
        return new Sha256Hash(message, salt).toString();
    }
}
