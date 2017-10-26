package org.txazo.java.io.charset;

import org.junit.Test;

/**
 * char
 */
public class CharTest {

    @Test
    public void test() throws Exception {
        printCharLength("a");
        printCharLength("你");
    }

    private void printCharLength(String str) throws Exception {
        System.out.println("----------------------------------------");
        System.out.println("字符串: " + str);
        printCharLength(str, "GBK");
        printCharLength(str, "GB2312");
        printCharLength(str, "UTF-8");
        printCharLength(str, "UTF-16");
        printCharLength(str, "UTF-16BE");
        printCharLength(str, "UTF-16LE");
        printCharLength(str, "UTF-32");
        System.out.println("----------------------------------------");
    }

    private void printCharLength(String str, String charset) throws Exception {
        System.out.println(charset + ": " + str.getBytes(charset).length);
    }

}
