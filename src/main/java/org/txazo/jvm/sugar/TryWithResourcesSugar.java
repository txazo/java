package org.txazo.jvm.sugar;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * try-with-resources - 语法糖
 */
public class TryWithResourcesSugar {

    @Test
    public void test() {
        try (InputStream is = new FileInputStream("/Users/txazo/t-stop.sh")) {
            is.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDecompile() {
        try {
            InputStream is = new FileInputStream("/Users/txazo/t-stop.sh");
            Throwable localThrowable2 = null;
            try {
                is.read();
            } catch (Throwable localThrowable1) {
                localThrowable2 = localThrowable1;
                throw localThrowable1;
            } finally {
                if (is != null) {
                    if (localThrowable2 != null) {
                        try {
                            is.close();
                        } catch (Throwable x2) {
                            localThrowable2.addSuppressed(x2);
                        }
                    } else {
                        is.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
