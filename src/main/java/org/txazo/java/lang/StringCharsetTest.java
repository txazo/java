package org.txazo.java.lang;

import org.junit.Test;
import org.txazo.security.hex.HexUtils;

import java.nio.charset.Charset;

/**
 * 字符串编码
 */
public class StringCharsetTest {

    @Test
    public void test1() {
        System.out.println("Default Charset: " + Charset.defaultCharset());
        String str = "hello北京";
        System.out.println(HexUtils.encodeHexString(str.getBytes()));
    }

    // -Dfile.encoding=GBK
    @Test
    public void test2() {
        System.out.println("Default Charset: " + Charset.defaultCharset());
        String str = "hello北京";
        System.out.println(HexUtils.encodeHexString(str.getBytes()));
    }

    @Test
    public void test3() throws Exception {
        String str = "hello北京";
        System.out.println(new String(str.getBytes("UTF-8"), "UTF-8"));
        System.out.println(new String(str.getBytes("GBK"), "GBK"));
        System.out.println(new String(str.getBytes("GBK"), "UTF-8"));
    }

}
