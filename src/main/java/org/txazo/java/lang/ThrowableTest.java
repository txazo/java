package org.txazo.java.lang;

import org.omg.SendingContext.RunTime;

/**
 * Error和Exception的父类
 */
public class ThrowableTest {

    public static void main(String[] args) {
        System.out.println(test1());
    }

    public static String test1() {
        try {
            System.out.println("try");
            throw new Exception("try");
        } catch (Exception e) {
            System.out.println("catch");
            return "catch";
//             throw new RuntimeException("catch");
        } finally {
            System.out.println("finally");
            return "finally";
//             throw new RuntimeException("finally");
        }
    }


    public int test() {
        try {
            return tryBlock();
        } catch (Exception e) {
            return catchBlock();
        } finally {
            finallyBlock();
        }
    }

    public int testInt() {
        try {
            return intTry();
        } catch (Exception e) {
            return intCatch();
        } finally {
            finallyBlock();
        }
    }

    private static int tryBlock() {
        return 1;
    }

    private static int catchBlock() {
        return 1;
    }

    private static void finallyBlock() {
    }

    private static int intTry() {
        return 0;
    }

    private static int intCatch() {
        return 0;
    }

    private static int intFinally() {
        return 0;
    }

}
