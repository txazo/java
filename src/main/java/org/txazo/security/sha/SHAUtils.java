package org.txazo.security.sha;

import org.txazo.security.common.MessageDigestUtils;

import java.security.NoSuchAlgorithmException;

/**
 * SHA
 * <p>
 * 1. 哈希算法
 * 2. 分类: SHA-1 SHA-224 SHA-256 SHA-384 SHA-512
 */
public abstract class SHAUtils {

    private static final String SHA_1 = "SHA-1";

    public static String sha1Hex(byte[] plainBytes) throws NoSuchAlgorithmException {
        return MessageDigestUtils.digestHex(SHA_1, plainBytes);
    }

}
