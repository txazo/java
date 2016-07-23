package org.txazo.java.io.io;

import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

/**
 * Closeable
 * <pre>
 * 1) 可以关闭的资源
 * 2) 调用close()释放资源
 * 3) 常见的实现类有:
 *    InputStream OutputStream
 *    Reader Writer
 *    Socket ServerSocket
 * </pre>
 */
public class CloseableTest {

    @Test
    public void test() {
        Resource resource = null;
        try {
            resource = new Resource();
        } finally {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try (Resource r = new Resource()) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Resource implements Closeable {

        private byte[] data;

        private Resource() {
            data = new byte[1024];
        }

        @Override
        public void close() throws IOException {
            data = null;
            System.out.println("Resource closed");
        }

    }

}
