package org.txazo.java.serialization;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializationTest {

    @Test
    public void test1() throws Exception {
        IParent object = new IChildren(1, "root");
        new ObjectOutputStream(new FileOutputStream("/Users/txazo/test/object.txt")).writeObject(object);
    }

    @Test
    public void test2() throws Exception {
        IChildren object = new IChildren(1, "root");
        new ObjectOutputStream(new FileOutputStream("/Users/txazo/test/object.txt")).writeObject(object);
    }

}
