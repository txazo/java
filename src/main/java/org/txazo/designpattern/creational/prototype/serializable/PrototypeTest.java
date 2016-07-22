package org.txazo.designpattern.creational.prototype.serializable;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.tool.util.lang.CloneUtils;

/**
 * 序列化实现深度克隆
 */
public class PrototypeTest {

    @Test
    public void test() throws CloneNotSupportedException {
        Student student = new Student(1, "txazo", new Teacher(1, "txazo"));
        Student clone = CloneUtils.clone(student);
        Assert.assertNotSame(student, clone);
        Assert.assertEquals("txazo", clone.getName());
        Assert.assertEquals("txazo", clone.getTeacher().getName());
    }

}
