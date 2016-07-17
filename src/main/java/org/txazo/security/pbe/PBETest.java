package org.txazo.security.pbe;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.security.common.CipherUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

/**
 * PBE
 * 1. 对称加密
 */
public class PBETest {

    private static final String PBEWITHMD5ANDDES = "PBEWITHMD5andDES";

    // 明文
    private String plainText = "security pbe";
    // 口令
    private String passwd = "1218";

    @Test
    public void testPBE() throws Exception {
        /** 生成盐 */
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(8);

        /** 生成密钥 */
        KeySpec keySpec = new PBEKeySpec(passwd.toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance(PBEWITHMD5ANDDES);
        Key key = factory.generateSecret(keySpec);

        /** 加密解密 */
        String encryptText = CipherUtils.encryptPBEHex(PBEWITHMD5ANDDES, key, plainText.getBytes(), salt, 100);
        String decryptText = CipherUtils.decryptPBEHex(PBEWITHMD5ANDDES, key, encryptText, salt, 100);
        Assert.assertEquals(plainText, decryptText);
    }

}
