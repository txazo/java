package org.txazo.jdk8;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Test {

    @Test
    public void test() throws UnsupportedEncodingException {
        String text = "Base64";
        String encode = Base64.getEncoder().encodeToString(text.getBytes());
        String decode = new String(Base64.getDecoder().decode(encode), "UTF-8");
        System.out.printf("encode: %s\n", encode);
        System.out.printf("decode: %s\n", decode);
    }

}
