package org.txazo.security.md5;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * <p>
 * 1. 单向加密
 * 2. 加密密码
 */
public abstract class MD5Utils {

    private static final String MD5 = "MD5";

    public static String md5Hex(byte[] plainBytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5);
        byte[] md5Bytes = md.digest(plainBytes);
        return Hex.encodeHexString(md5Bytes);
    }

}
