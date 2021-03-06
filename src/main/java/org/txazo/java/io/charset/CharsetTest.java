package org.txazo.java.io.charset;

import org.junit.Test;
import org.txazo.security.hex.HexUtils;

import java.io.UnsupportedEncodingException;

/**
 * 1) 编码
 *
 * ASCII: 单字节, 0x00 ~ 0x7f
 * ISO-8859-1: 单字节, 0x00 ~ 0xff, 向下兼容ASCII
 * GB2312: 中文编码, 英文一个字节, 中文两个字节
 * GBK: 中文编码, 英文一个字节, 中文两个字节, 向下兼容GB2312
 * UTF-8: 英文一个字节, 中文三个字节
 * UTF-16: 双字节
 * UTF-32: 四字节
 *
 * 2) 字符集
 *
 * Unicode: 两个字节, 每个字符对应一个唯一的Unicode编码
 */
public class CharsetTest {

    private static final String Charset_ASCII = "ASCII";
    private static final String Charset_ISO_8859_1 = "ISO-8859-1";
    private static final String Charset_GB2312 = "GB2312";
    private static final String Charset_GBK = "GBK";
    private static final String Charset_UTF_8 = "UTF-8";
    private static final String Charset_UTF_16 = "UTF-16";
    private static final String Charset_UTF_32 = "UTF-32";

    @Test
    public void testCharset() throws UnsupportedEncodingException {
        String text = "编码";
        byte[] bytes = text.getBytes(Charset_UTF_8);
        System.out.println(HexUtils.encodeHexString(bytes));
        System.out.println(new String(bytes, Charset_UTF_8));
    }

    @Test
    public void testChar() throws UnsupportedEncodingException {
        String text = "I神";

        // 49 3F
        printHex(text, Charset_ISO_8859_1);
        // 49 C9F1
        printHex(text, Charset_GB2312);
        // 49 C9F1
        printHex(text, Charset_GBK);
        // 49 E7A59E
        printHex(text, Charset_UTF_8);
        // FEFF00 47795E
        printHex(text, Charset_UTF_16);
        // 0000000049 0000795E
        printHex(text, Charset_UTF_32);
    }

    private void printHex(String text, String charset) throws UnsupportedEncodingException {
        System.out.println(HexUtils.encodeHexString(text.getBytes(charset)));
    }

    @Test
    public void testConvertCharset() throws UnsupportedEncodingException {
        String text = "I am 洲神";

        System.out.println(convertCharset(text, Charset_GBK, Charset_GBK));
        System.out.println(convertCharset(text, Charset_GBK, Charset_ASCII));
        System.out.println(convertCharset(text, Charset_GBK, Charset_UTF_8));
        System.out.println(convertCharset(text, Charset_GBK, Charset_UTF_16));
    }

    private String convertCharset(String src, String srcCharset, String destCharset) throws UnsupportedEncodingException {
        return new String(src.getBytes(srcCharset), destCharset);
    }

}
