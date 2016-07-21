package org.txazo.java.collection.iterator;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Iterator
 * <p>
 * 1) 迭代器
 * 2) remove()是安全的
 */
public class IteratorTest {

    @Test
    public void test() {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (Iterator<Integer> i = list.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
            i.remove();
        }
    }

}
