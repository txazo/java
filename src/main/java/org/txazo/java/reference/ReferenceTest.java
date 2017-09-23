package org.txazo.java.reference;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * @author xztu
 * @date 2017-09-23
 */
public class ReferenceTest {

    /**
     * @see java.lang.ref.Reference.ReferenceHandler
     */
    @Test
    public void test() {
        Object obj = new Object();
        WeakReference<Object> reference = new WeakReference<>(obj);
        Assert.assertNotNull(reference.get());
        obj = null;
        System.gc();
        Assert.assertNull(reference.get());
    }

}
