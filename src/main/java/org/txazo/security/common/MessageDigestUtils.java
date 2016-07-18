package org.txazo.security.common;

import org.txazo.security.hex.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MessageDigestUtils {

    private static final ConcurrentHashMap<String, MessageDigest> digestMap = new ConcurrentHashMap<String, MessageDigest>();

    public static String digestHex(String algorithm, byte[] plainBytes) throws NoSuchAlgorithmException {
        return HexUtils.encodeHexString(digest(algorithm, plainBytes));
    }

    public static byte[] digest(String algorithm, byte[] plainBytes) throws NoSuchAlgorithmException {
        MessageDigest md = getMessageDigest(algorithm);
        return md.digest(plainBytes);
    }

    private static MessageDigest getMessageDigest(String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = digestMap.get(algorithm);
        if (md == null) {
            md = MessageDigest.getInstance(algorithm);
            MessageDigest origin = digestMap.putIfAbsent(algorithm, md);
            if (origin != null) {
                md = origin;
            }
        }
        return md;
    }

}
