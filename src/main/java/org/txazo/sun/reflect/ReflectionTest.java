package org.txazo.sun.reflect;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.AbstractTest;
import sun.reflect.Reflection;

public class ReflectionTest extends AbstractTest {

    @Test
    public void testGetCallerClass() {
        call();
    }

    private void call() {
        Assert.assertSame(ReflectionTest.class, Reflection.getCallerClass());
    }

}
