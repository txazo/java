package org.txazo.java.lang;

import org.junit.Test;

/**
 * String.intern()
 *
 * 1) String常量池中已存在, 返回已存在的String
 * 2) String常量池中不存在, 添加到String常量池并返回
 */
public class StringInternTest {

    public static void main(String[] args) {
        // true
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1 == str1.intern());

        // false
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2 == str2.intern());
    }

    @Test
    public void test() {
        // 字符串常量
        String str1 = "I am";
        String str2 = "I " + "am";
        // intern()
        String str3 = str2.intern();
        // StringBuilder
        String str4 = new String("I am");
        String str5 = new String("I ") + new String("am");
        System.out.println(str3 == str1);
        System.out.println(str3 == str2);
        System.out.println(str3 == str4);
        System.out.println(str3 == str5);
    }

}
