package org.txazo.security.key;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Key {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Key(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getPublicKeyHexString() {
        return Hex.encodeHexString(publicKey.getEncoded());
    }

    public String getPrivateHexString() {
        return Hex.encodeHexString(privateKey.getEncoded());
    }

    public String getPublicKeyBase64String() {
        return Base64.encodeBase64String(publicKey.getEncoded());
    }

    public String getPrivateBase64String() {
        return Base64.encodeBase64String(privateKey.getEncoded());
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

}
