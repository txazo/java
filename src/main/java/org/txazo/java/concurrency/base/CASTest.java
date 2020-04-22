package org.txazo.java.concurrency.base;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CAS - Compare And Swap
 * <p>
 * 1) 乐观锁
 * 2) 原子操作
 * 3) 非阻塞算法
 * <p>
 * CAS缺点
 * 1) ABA问题
 * 2) 循环时间长开销大, CAS自旋
 * 3) 只能保证一个共享变量的原子操作
 */
public class CASTest {

    public static final Unsafe unsafe;
    private static final long valueOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            valueOffset = unsafe.objectFieldOffset(CASTest.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private volatile int value = 0;

    private int incr() {
        int expected;
        int newValue;
        do {
            expected = value;
            expected = unsafe.getIntVolatile(this, valueOffset);
            newValue = expected + 1;
        } while (!unsafe.compareAndSwapInt(this, valueOffset, expected, newValue));
        return newValue;
    }

    @Test
    public void test1() throws Exception {
        incr();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000000; j++) {
                    incr();
                }
            });
        }
        Thread.sleep(5000);
        System.out.print(value);
    }

}
