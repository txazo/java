package org.txazo.security.des;

import org.junit.Assert;
import org.junit.Test;

public class DESTest {

    // 明文
    private String plainText = "security des";
    // 密文
    private String cipherText = "2f66f8400cd43ef7c34e8b217e1d773f";
    // 密钥
    private String secretKey = "cbabe019ab9264a8";

    @Test
    public void testDES() throws Exception {
        String encryptText = DESUtils.encryptHex(secretKey, plainText);
        Assert.assertEquals(cipherText, encryptText);
        String decryptText = DESUtils.decryptHex(secretKey, encryptText);
        Assert.assertEquals(plainText, decryptText);
    }

}
