package org.txazo.java.collection.deque;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @see java.util.ArrayDeque
 */
public class ArrayDequeTest {

    @Test
    public void test() {
        ArrayDeque<Integer> deque = new ArrayDeque();
        for (int i = 0; i < 20; i++) {
            deque.add(i);
        }
        System.out.println(deque);
    }

}
