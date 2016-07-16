package org.txazo.security.url;

import org.junit.Assert;
import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL编码
 * <p>
 * 1. HTML表单提交
 * 2. 编码规则: 数字和字母不变, 空格变为"+", 其它被编码成"%"加上对应ascii的十六进制
 */
public class URLTest {

    // 明文
    private String plainText = "security url 编码";

    // 密文
    private String cipherText = "security+url+%E7%BC%96%E7%A0%81";

    @Test
    public void testURL() throws Exception {
        /** URL Encode: 字符串以URL编码 */
        String encryptText = URLEncoder.encode(plainText, "UTF-8");
        Assert.assertEquals(cipherText, encryptText);
        /** URL Decode: 字符串进行URL解码 */
        String decryptText = URLDecoder.decode(cipherText, "UTF-8");
        Assert.assertEquals(plainText, decryptText);
    }

}
