package org.txazo.jvm.sugar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 增强for循环 - 语法糖
 * <p>
 * 1) foreach原理: Iterator
 */
public class ForEachTest {

    @Test
    public void test() {
        int number = 0;
        List<Integer> list = new ArrayList<>();
        for (Integer i : list) {
            number = i;
        }
    }

    @Test
    public void testDecompile() {
        int number = 0;
        List list = new ArrayList();
        for (Iterator i = list.iterator(); i.hasNext(); ) {
            number = ((Integer) i.next()).intValue();
        }
    }

}
