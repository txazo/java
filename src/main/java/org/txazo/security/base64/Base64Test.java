package org.txazo.security.base64;

import org.junit.Assert;
import org.junit.Test;

public class Base64Test {

    // 明文
    private String plainText = "security base64";
    // 密文
    private String cipherText = "c2VjdXJpdHkgYmFzZTY0";

    @Test
    public void testBase64() throws Exception {
        Assert.assertEquals(cipherText, Base64Utils.encode(plainText.getBytes()));
        Assert.assertEquals(plainText, new String(Base64Utils.decode(cipherText)));
    }

}
