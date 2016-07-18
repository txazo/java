package org.txazo.security.urlencode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * urlencode
 * <p>
 * 1. 编码解码
 * 2. 原理: 数字和字母不变, 空格变为+, 其它被编码成%加上对应ascii的十六进制
 * 3. 应用: HTML表单提交
 */
public abstract class URLEncodeUtils {

    private static final String UTF_8 = "UTF-8";

    public static String encode(String plainText) throws UnsupportedEncodingException {
        return URLEncoder.encode(plainText, UTF_8);
    }

    public static String decode(String cipherText) throws UnsupportedEncodingException {
        return URLDecoder.decode(cipherText, UTF_8);
    }

}
