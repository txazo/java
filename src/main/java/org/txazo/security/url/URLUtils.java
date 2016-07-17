package org.txazo.security.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL编码
 * <p>
 * 1. HTML表单提交
 * 2. 编码规则: 数字和字母不变, 空格变为+, 其它被编码成%加上对应ascii的十六进制
 */
public abstract class URLUtils {

    private static final String UTF_8 = "UTF-8";

    public static String encode(String plainText) throws UnsupportedEncodingException {
        return URLEncoder.encode(plainText, UTF_8);
    }

    public static String decode(String cipherText) throws UnsupportedEncodingException {
        return URLDecoder.decode(cipherText, UTF_8);
    }

}
