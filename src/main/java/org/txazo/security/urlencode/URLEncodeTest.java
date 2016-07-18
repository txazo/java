package org.txazo.security.urlencode;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class URLEncodeTest {

    // 明文
    private String plainText = "security url 编码";
    // 密文
    private String cipherText = "security+url+%E7%BC%96%E7%A0%81";

    @Test
    public void testURLEncode() throws UnsupportedEncodingException {
        Assert.assertEquals(cipherText, URLEncodeUtils.encode(plainText));
        Assert.assertEquals(plainText, URLEncodeUtils.decode(cipherText));
    }

}
