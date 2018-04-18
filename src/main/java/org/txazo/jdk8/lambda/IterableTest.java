package org.txazo.jdk8.lambda;

import org.junit.Test;

import java.util.Arrays;

public class IterableTest {

    @Test
    public void test() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer i : iterable) {
            System.out.println(i);
        }
    }

    @Test
    public void testForEach() {
        Arrays.asList(1, 2, 3, 4, 5).forEach(
                i -> System.out.println(i)
        );
    }

}
