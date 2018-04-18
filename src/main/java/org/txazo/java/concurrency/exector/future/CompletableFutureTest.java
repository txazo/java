package org.txazo.java.concurrency.exector.future;

import org.junit.Test;

import java.util.concurrent.*;

public class CompletableFutureTest {

    @Test
    public void test() {
        CompletableFuture future = CompletableFuture.anyOf(
                CompletableFuture.runAsync(() -> {
                    System.out.println("Task 1");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }),
                CompletableFuture.runAsync(() -> {
                    System.out.println("Task 2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }),
                CompletableFuture.runAsync(() -> {
                    System.out.println("Task 3");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );
        future.join();
        System.out.println("Task Complete");
    }

    @Test
    public void test1() {
        ExecutorService executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));
        CompletableFuture.runAsync(() -> System.out.println("Test"), executor).join();
    }

}
