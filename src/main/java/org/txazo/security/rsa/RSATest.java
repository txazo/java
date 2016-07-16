package org.txazo.security.rsa;

import org.junit.Assert;
import org.junit.Test;

public class RSATest {

    // 明文
    private String plainText = "security rsa";
    // 私钥加密密文
    private String privateCipherText = "06ef88eda03391e9713bcc042aa36a64e086ced669d39cf6972b6a125f648148b944295a9d13fabdf66b71771d89baa9bb0022e90594f8233134aa1090d97619";
    // 公钥
    private String publicSecretKey = "305c300d06092a864886f70d0101010500034b00304802410094360cd1413b66819ce4737851305ea2af6d0a853d975151714e32d43a6610f8b89c22e1429cdf95d3b795bfc97eb38b6886fb70aeda08a39d8360b480701bf50203010001";
    // 私钥
    private String privateSecretKey = "30820155020100300d06092a864886f70d01010105000482013f3082013b02010002410094360cd1413b66819ce4737851305ea2af6d0a853d975151714e32d43a6610f8b89c22e1429cdf95d3b795bfc97eb38b6886fb70aeda08a39d8360b480701bf5020301000102410083a269c9e4b4fc3afbfccf2dba764a671585b44558c9af78b538adde5eabce3faf7bfb8e44e37c1fbfb9b98855dfb31062390a1b88cb822dde68029172e43ca1022100ca69e6a00ba1b22ce952cd9beb60cb702e29406930d4a53b927e3d9e547d4cc9022100bb72b3d67f55d815f2385f8256438cdcf31e982e3d6bef3741bcc0d824af27cd0220793cd12c4f8b3c437645686dd9a0127a50cbd88ea8f6d539475761e4f1ca5331022006d6b576f2e857dcdd1c6f679aec15fc8da6251932c67850c4c63526ed97b255022100c432f3f43cbb71d3466a4a7f0b75d5574986b4975759065c68f29e42545ed5d7";

    @Test
    public void testRSA() throws Exception {
        /** 私钥加密、公钥解密 */
        String encryptText = RSAUtils.privateEncryptHex(privateSecretKey, plainText);
        Assert.assertEquals(privateCipherText, encryptText);
        String decryptText = RSAUtils.publicDecryptHex(publicSecretKey, privateCipherText);
        Assert.assertEquals(plainText, decryptText);

        /** 公钥加密、私钥解密 */
        encryptText = RSAUtils.publicEncryptHex(publicSecretKey, plainText);
        decryptText = RSAUtils.privateDecryptHex(privateSecretKey, encryptText);
        Assert.assertEquals(plainText, decryptText);
    }

}
