package org.txazo.designpattern.creational.prototype.cloneable;

import org.junit.Assert;
import org.junit.Test;

/**
 * Java的clone()实现深度克隆
 */
public class PrototypeTest {

    @Test
    public void test() throws CloneNotSupportedException {
        Student student = new Student(1, "txazo", new Teacher(1, "txazo"));
        Student clone = student.clone();
        Assert.assertNotSame(student, clone);
        Assert.assertEquals("txazo", clone.getName());
        Assert.assertEquals("txazo", clone.getTeacher().getName());
    }

}
