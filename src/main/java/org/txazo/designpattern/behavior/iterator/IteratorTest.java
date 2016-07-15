package org.txazo.designpattern.behavior.iterator;

import org.junit.Assert;
import org.junit.Test;

public class IteratorTest {

    @Test
    public void test() {
        Tuple<String> tuple = new Tuple<String>();
        tuple.add("root");
        tuple.add("txazo");
        tuple.add("admin");
        tuple.add("manager");
        Iterator<String> iterator = tuple.iterator();
        Assert.assertEquals(iterator.first(), "root");
        Assert.assertEquals(iterator.last(), "manager");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
    }

}
