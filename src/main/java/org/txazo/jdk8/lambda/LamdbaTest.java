package org.txazo.jdk8.lambda;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        System.out.println(((Runnable) () -> {
        }).getClass());
    }

    /**
     * @see java.lang.Runnable
     */
    @Test
    public void testRunnable() {
        new Thread(() -> System.out.println("Thread start")).start();
    }

    /**
     * @see java.util.Comparator
     */
    @Test
    public void testComparator() {
        List<Integer> list = Arrays.asList(1, 3, 2, 5, 4);
        Collections.sort(list, (Integer i1, Integer i2) -> i1 - i2);
        list.stream().forEach(System.out::println);
    }

    private static boolean filterStatic(Integer i) {
        return i >= 2 && i <= 4;
    }

    private boolean filterInstance(Integer i) {
        return i >= 2 && i <= 4;
    }

    public void forEach() {
        System.out.println("");
    }

    /**
     * 方法引用
     */
    @Test
    public void testMethodReference() {
        LamdbaTest test = new LamdbaTest();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // 实例方法引用
        list.stream().filter(test::filterInstance).forEach(System.out::println);
        // 静态方法引用
        list.stream().filter(LamdbaTest::filterStatic).forEach(System.out::println);
        // 构造函数引用
        list.stream().map(Number::new).forEach(System.out::println);

        Print<String> print = System.out::println;
        print.print("Lambda");
    }

    /**
     * @see java.util.function.Consumer
     */
    @Test
    public void testConsumer() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().forEach(System.out::println);
    }

    /**
     * @see java.util.function.Predicate
     */
    @Test
    public void testPredicate() {
        Predicate<Integer> p1 = i -> i >= 2;
        Predicate<Integer> p2 = i -> i <= 4;
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().filter(p1.and(p2)).forEach(System.out::println);
    }

    /**
     * @see java.util.function.Function
     */
    @Test
    public void testFunction() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map(Number::new).forEach(System.out::println);
    }

    /**
     * 集合reduce
     */
    @Test
    public void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int total = list.stream().reduce((x, y) -> x + y).get();
        Assert.assertEquals(15, total);
    }

    /**
     * 集合collect
     */
    @Test
    public void testCollect() {
        List<Integer> list = Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        List<Integer> result = list.stream().filter(i -> i >= 2 && i <= 4).distinct().collect(Collectors.toList());
        result.stream().forEach(System.out::println);

        Map<Integer, Integer> map = result.stream().collect(Collectors.toMap(k -> k, v -> v * v));
        map.entrySet().forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    @Test
    public void testGroupBy() {
        List<Integer> list = Arrays.asList(1, 1, 1, 2, 2, 3);
        Map<Integer, Integer> map = list.stream().collect(Collectors.groupingBy(p -> p, Collectors.summingInt(p -> 1)));
        map.entrySet().forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    /**
     * 嵌套Lambda表达式
     */
    @Test
    public void testNestLambda() throws Exception {
        Callable<Runnable> c = () -> () -> System.out.println("Thread start");
        c.call().run();
    }

    /**
     * Lambda表达式访问外部变量: 外部变量的值不改变即可
     */
    @Test
    public void testVariable() throws Exception {
        int i = 2;
        Callable<Integer> c = () -> i * i;
        Assert.assertEquals(Integer.valueOf(4), c.call());
    }

    private static class Number {

        private int value;

        public Number(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

    }

    @FunctionalInterface
    private static interface Print<T> {

        void print(T t);

    }

}
