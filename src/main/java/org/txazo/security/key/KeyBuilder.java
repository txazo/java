package org.txazo.security.key;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public abstract class KeyBuilder {

    private static final int DEFAULT_KEY_SIZE = 1024;

    public static PublicKey getPublicKeyHex(String algorithm, String publicKey) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Hex.decodeHex(publicKey.toCharArray()));
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKeyHex(String algorithm, String privateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Hex.decodeHex(privateKey.toCharArray()));
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(keySpec);
    }

    public static Key buildKey(String algorithm) throws NoSuchAlgorithmException {
        return buildKey(algorithm, DEFAULT_KEY_SIZE);
    }

    public static Key buildKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new Key(keyPair.getPublic(), keyPair.getPrivate());
    }

    public static byte[] buildSecretKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static String buildSecretKeyBase64String(String algorithm) throws NoSuchAlgorithmException {
        return Base64.encodeBase64String(buildSecretKey(algorithm));
    }

    public static String buildSecretKeyHexString(String algorithm) throws NoSuchAlgorithmException {
        return Hex.encodeHexString(buildSecretKey(algorithm));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(buildSecretKeyHexString("HmacMD5"));
        System.out.println(buildSecretKeyHexString("DES"));
        System.out.println(buildSecretKeyHexString("AES"));
    }

}
