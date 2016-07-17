package org.txazo.security.md5;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class MD5Test {

    // 明文
    private String plainText = "security md5";
    // MD5值
    private String md5 = "b7b76c2429ca5d5e0165790393103bf7";

    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        Assert.assertEquals(md5, MD5Utils.md5Hex(plainText.getBytes()));
    }

}
