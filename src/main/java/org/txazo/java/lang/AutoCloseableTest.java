package org.txazo.java.lang;

import org.junit.Test;

/**
 * AutoCloseable
 * <p/>
 * 1) try-with-resource语法下自动关闭
 */
public class AutoCloseableTest {

    @Test
    public void test() {
        try (Resource resource = new Resource()) {
            resource.load("AutoCloseable");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Resource implements AutoCloseable {

        private byte[] data;

        public Resource() {
            data = new byte[1024];
        }

        public void load(String resource) {
            data = resource.getBytes();
        }

        @Override
        public void close() throws Exception {
            data = null;
            System.out.println("Resource closed");
        }

    }

}
