package org.txazo.java.reflection;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Modifier;

/**
 * ReflectionModifier
 *
 * @author txazo
 * @email txazo1218@163.com
 * @see Modifier
 * @since 13.05.2015
 */
public class ReflectionModifier {

    @Test
    public void test1() {
        Assert.assertTrue(Modifier.isPublic(ReflectionModifier.class.getModifiers()));
    }

}
