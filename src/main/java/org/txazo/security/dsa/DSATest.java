package org.txazo.security.dsa;

import org.junit.Assert;
import org.junit.Test;

/**
 * DSA
 * <p>
 * 1. 数字签名算法
 */
public class DSATest {

    // 明文
    private String plainText = "security dsa";
    // 公钥
    private String publicSecretKey = "3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4034300024059b90c00a53e11bc4a7a9030dba8bc3a10807fc83e70b210a2a750250458892c3537ca930928f44c1e4a77094c22f6b24537d5465466e98f2a780edcb1280666";
    // 私钥
    private String privateSecretKey = "3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4041602147fa85f3e938e15da8463170380ce5012124fcc8a";

    @Test
    public void testSHA1WithDSA() throws Exception {
        /** 签名 */
        String encryptText = DSAUtils.signWithSHA1Hex(privateSecretKey, plainText);
        /** 验证 */
        boolean verify = DSAUtils.verifyWithSHA1Hex(publicSecretKey, plainText, encryptText);
        Assert.assertTrue(verify);
    }

}
