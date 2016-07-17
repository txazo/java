package org.txazo.security.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * base64
 * <p>
 * 1. 双向编码解码
 * 2. A-Z a-z 0-9 + / =
 * 3. 3个8位字节转换为4个8位字节(3 * 8 = 4 * 6, 高两位补0, 不足4字节以=补充)
 */
public abstract class Base64Utils {

    private static final BASE64Encoder ENCODER = new BASE64Encoder();
    private static final BASE64Decoder DECODER = new BASE64Decoder();

    public static String encode(byte[] plainBytes) {
        return ENCODER.encode(plainBytes);
    }

    public static byte[] decode(String cipherText) throws IOException {
        return DECODER.decodeBuffer(cipherText);
    }

}
