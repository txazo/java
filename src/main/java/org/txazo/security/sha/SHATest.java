package org.txazo.security.sha;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class SHATest {

    // 明文
    private String plainText = "security sha1";
    // SHA1值
    private String sha1 = "528b6062db1e513304cc28bd5019e25a57a68b7c";

    @Test
    public void testSHA1() throws NoSuchAlgorithmException {
        Assert.assertEquals(sha1, SHAUtils.sha1Hex(plainText.getBytes()));
    }

}
