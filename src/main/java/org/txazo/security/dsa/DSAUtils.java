package org.txazo.security.dsa;

import org.txazo.security.key.Key;
import org.txazo.security.key.KeyBuilder;
import org.txazo.security.signature.SignatureUtils;

public abstract class DSAUtils {

    private static final String DSA = "DSA";
    private static final String SHA1WITHDSA = "SHA1withDSA";

    /**
     * DSA签名
     *
     * @param privateKey 私钥
     * @param plainText  明文
     */
    public static String signWithSHA1Hex(String privateKey, String plainText) throws Exception {
        return SignatureUtils.signHex(SHA1WITHDSA, KeyBuilder.getPrivateKeyHex(DSA, privateKey), plainText);
    }

    /**
     * DSA验证
     *
     * @param publicKey     公钥
     * @param plainText     明文
     * @param signatureText 签名
     */
    public static boolean verifyWithSHA1Hex(String publicKey, String plainText, String signatureText) throws Exception {
        return SignatureUtils.verifyHex(SHA1WITHDSA, KeyBuilder.getPublicKeyHex(DSA, publicKey), plainText, signatureText);
    }

    public static void main(String[] args) throws Exception {
        Key key = KeyBuilder.buildKey(DSA, 512);
        System.out.println(key.getPublicKeyHexString());
        System.out.println(key.getPrivateHexString());
    }

}
