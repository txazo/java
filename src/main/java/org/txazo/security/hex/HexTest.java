package org.txazo.security.hex;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

/**
 * Hex
 * <p>
 * 1. 二进制的8位转换为十六进制的16位
 */
public class HexTest {

    // 明文
    private String plainText = "security hex";
    // 密文
    private String cipherText = "736563757269747920686578";

    @Test
    public void testCodecHex() throws DecoderException {
        /** 十六机制编码 */
        String encryptText = Hex.encodeHexString(plainText.getBytes());
        Assert.assertEquals(cipherText, encryptText);
        /** 十六机制解码 */
        String decryptText = new String(Hex.decodeHex(encryptText.toCharArray()));
        Assert.assertEquals(plainText, decryptText);

    }

}
