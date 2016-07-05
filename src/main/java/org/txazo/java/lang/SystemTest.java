package org.txazo.java.lang;

import org.junit.Test;
import org.txazo.java.test.AbstractTest;

public class SystemTest extends AbstractTest {

    /**
     * System.identityHashCode()
     * <p/>
     * 返回的值和默认的Object的hashCode()的返回值一样
     */
    @Test
    public void testIdentityHashCode() {
        Object o = new Object();
        HashCode h = new HashCode();
        LOG.info("{} {}", o.hashCode(), System.identityHashCode(o));
        LOG.info("{} {}", h.hashCode(), System.identityHashCode(h));
    }

    private static class HashCode {

        @Override
        public int hashCode() {
            return 1000;
        }

    }

}
