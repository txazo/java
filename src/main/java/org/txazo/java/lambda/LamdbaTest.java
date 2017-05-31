package org.txazo.java.lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class LamdbaTest {

    /**
     * 函数式接口
     */
    @Test
    public void testFunctionalInterface() {
        Runnable r = () -> {
        };
        Object obj = r;
        Assert.assertNotNull(r);
        Assert.assertNotNull(obj);

        System.out.println((Runnable) () -> {
        });
    }

    @Test
    public void testRunnable() {
        new Thread(() -> System.out.println("Thread start")).start();
    }

    @Test
    public void testComparator() {
        List<Integer> list = Arrays.asList(1, 3, 2, 5, 4);
        Collections.sort(list, (Integer i1, Integer i2) -> i1 - i2);
    }

    private static boolean filter(Integer i) {
        return i >= 2 && i <= 4;
    }

    /**
     * 方法引用
     */
    @Test
    public void testMethodReference() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().filter(LamdbaTest::filter).forEach(i -> System.out.println(i));
    }

    @Test
    public void testConsumer() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().forEach(i -> System.out.println(i));
    }

    @Test
    public void testPredicate() {
        Predicate<Integer> p1 = i -> i >= 2;
        Predicate<Integer> p2 = i -> i <= 4;
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().filter(p1.and(p2)).forEach(i -> System.out.println(i));
    }

    @Test
    public void testFunction() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map(i -> new Number(i)).forEach(n -> System.out.println(n.value()));
    }

    @Test
    public void testBiFunction() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int total = list.stream().reduce((x, y) -> x + y).get();
        Assert.assertEquals(15, total);
    }

//    @Test
//    public void testSupplier() {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        list.stream().collect()
//    }

    private static class Number {

        private int value;

        public Number(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

    }

}
