package org.txazo.security.sha;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * SHA
 * <p>
 * 1. 单向加密
 * 2. SSL证书
 * 3. Open API(key + timestamp)
 */
public class SHATest {

    private static final String SHA1 = "SHA1";

    // 明文
    private String plainText = "security sha1";
    // SHA1值
    private String sha1 = "528b6062db1e513304cc28bd5019e25a57a68b7c";

    @Test
    public void testJdkSHA1() throws Exception {
        MessageDigest md = MessageDigest.getInstance(SHA1);
        byte[] sha1Bytes = md.digest(plainText.getBytes());
        Assert.assertEquals(sha1, Hex.encodeHexString(sha1Bytes));
    }

    @Test
    public void testCodecSHA1() {
        Assert.assertEquals(sha1, DigestUtils.sha1Hex(plainText));
    }

}
