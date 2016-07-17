package org.txazo.security.common;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;

/**
 * CipherUtils
 */
public abstract class CipherUtils {

    /**
     * 加密
     *
     * @param algorithm 算法
     * @param key       key
     * @param plainText 明文
     */
    public static String encryptHex(String algorithm, Key key, String plainText) throws Exception {
        return encryptHex(algorithm, key, plainText.getBytes());
    }

    /**
     * 加密
     *
     * @param algorithm  算法
     * @param key        key
     * @param plainBytes 明文
     */
    public static String encryptHex(String algorithm, Key key, byte[] plainBytes) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptBytes = cipher.doFinal(plainBytes);
        return Hex.encodeHexString(encryptBytes);
    }

    /**
     * 解密
     *
     * @param algorithm  算法
     * @param key        key
     * @param cipherText 密文
     */
    public static String decryptHex(String algorithm, Key key, String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptBytes = cipher.doFinal(Hex.decodeHex(cipherText.toCharArray()));
        return new String(decryptBytes);
    }

    /**
     * PBE加密
     *
     * @param algorithm      算法
     * @param key            key
     * @param plainBytes     明文
     * @param salt           盐
     * @param iterationCount 循环次数
     */
    public static String encryptPBEHex(String algorithm, Key key, byte[] plainBytes, byte[] salt, int iterationCount) throws Exception {
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, iterationCount);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
        byte[] encryptBytes = cipher.doFinal(plainBytes);
        return Hex.encodeHexString(encryptBytes);
    }

    /**
     * PBE解密
     *
     * @param algorithm      算法
     * @param key            key
     * @param cipherText     密文
     * @param salt           盐
     * @param iterationCount 循环次数
     */
    public static String decryptPBEHex(String algorithm, Key key, String cipherText, byte[] salt, int iterationCount) throws Exception {
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, iterationCount);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
        byte[] decryptBytes = cipher.doFinal(Hex.decodeHex(cipherText.toCharArray()));
        return new String(decryptBytes);
    }

}
