package org.txazo.security.md5;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * MD5
 * <p>
 * 1. 单向加密
 * 2. 加密密码
 */
public class MD5Test {

    private static final String MD5 = "MD5";

    // 明文
    private String plainText = "security md5";

    // MD5值
    private String md5 = "b7b76c2429ca5d5e0165790393103bf7";

    @Test
    public void testJdkMD5() throws Exception {
        MessageDigest md = MessageDigest.getInstance(MD5);
        byte[] md5Bytes = md.digest(plainText.getBytes());
        Assert.assertEquals(md5, Hex.encodeHexString(md5Bytes));
    }

    @Test
    public void testCodecMD5() {
        Assert.assertEquals(md5, DigestUtils.md5Hex(plainText));
    }

}
