package org.txazo.security.aes;

import org.apache.commons.codec.binary.Hex;
import org.txazo.security.common.CipherUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * AES
 * <p>
 * 1. 对称加密
 */
public abstract class AESUtils {

    private static final String AES = "AES";

    private static Key generateKey(byte[] keyBytes) throws Exception {
        return new SecretKeySpec(keyBytes, AES);
    }

    /**
     * AES加密
     *
     * @param secretKey 密钥
     * @param plainText 明文
     */
    public static String encryptHex(String secretKey, String plainText) throws Exception {
        return encryptHex(Hex.decodeHex(secretKey.toCharArray()), plainText.getBytes());
    }

    /**
     * AES加密
     *
     * @param secretBytes 密钥
     * @param plainBytes  明文
     */
    public static String encryptHex(byte[] secretBytes, byte[] plainBytes) throws Exception {
        return CipherUtils.encryptHex(AES, generateKey(secretBytes), plainBytes);
    }

    /**
     * AES解密
     *
     * @param secretKey  密钥
     * @param cipherText 密文
     */
    public static String decryptHex(String secretKey, String cipherText) throws Exception {
        return decryptHex(Hex.decodeHex(secretKey.toCharArray()), cipherText);
    }

    /**
     * AES解密
     *
     * @param secretBytes 密钥
     * @param cipherText  密文
     */
    public static String decryptHex(byte[] secretBytes, String cipherText) throws Exception {
        return CipherUtils.decryptHex(AES, generateKey(secretBytes), cipherText);
    }

}
