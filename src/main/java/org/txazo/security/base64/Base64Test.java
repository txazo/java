package org.txazo.security.base64;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class Base64Test {

    // 明文
    private String plainText = "security base64";
    // 密文
    private String cipherText = "c2VjdXJpdHkgYmFzZTY0";

    @Test
    public void testBase64() throws IOException {
        Assert.assertEquals(cipherText, Base64Utils.encode(plainText.getBytes()));
        Assert.assertArrayEquals(plainText.getBytes(), Base64Utils.decode(cipherText));
    }

}
