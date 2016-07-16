package org.txazo.security.hmac;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacUtils;
import org.junit.Assert;
import org.junit.Test;

public class HMACTest {

    // 明文
    private String plainText = "security hmac";
    // 密文
    private String cipherText = "c291129597869e134ad76699b956908f";
    // 密钥
    private String secretKey = "9b472aad41e7b6981c94e78eca8a90abfae9dc2248f1efe45fb0f64c7cca2f8d3d0f4f621a14d6d15768f06b8925cebe1de3364ade2f8ae4f5a3c63c78e94026";

    @Test
    public void testJdkHmacMD5() throws Exception {
        String encryptText = HMACUtils.hmacHex(HmacAlgorithm.HMAC_MD5, secretKey, plainText);
        Assert.assertEquals(cipherText, encryptText);
    }

    @Test
    public void testCodecHmacMD5() throws DecoderException {
        String encryptText = HmacUtils.hmacMd5Hex(Hex.decodeHex(secretKey.toCharArray()), plainText.getBytes());
        Assert.assertEquals(cipherText, encryptText);
    }

}
