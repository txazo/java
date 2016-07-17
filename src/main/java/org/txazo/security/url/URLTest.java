package org.txazo.security.url;

import org.junit.Assert;
import org.junit.Test;

public class URLTest {

    // 明文
    private String plainText = "security url 编码";
    // 密文
    private String cipherText = "security+url+%E7%BC%96%E7%A0%81";

    @Test
    public void testURL() throws Exception {
        Assert.assertEquals(cipherText, URLUtils.encode(plainText));
        Assert.assertEquals(plainText, URLUtils.decode(cipherText));
    }

}
