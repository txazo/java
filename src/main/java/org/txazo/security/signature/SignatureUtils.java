package org.txazo.security.signature;

import org.apache.commons.codec.binary.Hex;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * 数字签名
 * 1. 私钥签名、公钥验证
 * 2. RSA、DSA、ECDSA
 */
public abstract class SignatureUtils {

    /**
     * 签名
     *
     * @param algorithm  算法
     * @param privateKey 私钥
     * @param plainText  明文
     */
    public static String signHex(String algorithm, PrivateKey privateKey, String plainText) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(plainText.getBytes());
        return Hex.encodeHexString(signature.sign());
    }

    /**
     * 验证
     *
     * @param algorithm     算法
     * @param publicKey     公钥
     * @param plainText     明文
     * @param signatureText 签名
     */
    public static boolean verifyHex(String algorithm, PublicKey publicKey, String plainText, String signatureText) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);
        signature.update(plainText.getBytes());
        return signature.verify(Hex.decodeHex(signatureText.toCharArray()));
    }

}
