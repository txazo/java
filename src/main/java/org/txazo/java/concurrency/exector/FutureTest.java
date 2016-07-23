package org.txazo.java.concurrency.exector;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {

    @Test
    public void testFuture() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Boolean> future = executor.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                return true;
            }

        });

        try {
            Boolean result = future.get();
            Assert.assertTrue(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFutureTask() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                Thread.sleep(500);
                return false;
            }

        });
        executor.execute(futureTask);
        try {
            Boolean result = futureTask.get(1000, TimeUnit.MILLISECONDS);
            Assert.assertFalse(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
