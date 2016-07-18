package org.txazo.security.md5;

import org.txazo.security.common.MessageDigestUtils;

import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * <p>
 * 1. 哈希算法
 */
public abstract class MD5Utils {

    private static final String MD5 = "MD5";

    public static String md5Hex(byte[] plainBytes) throws NoSuchAlgorithmException {
        return MessageDigestUtils.digestHex(MD5, plainBytes);
    }

}
