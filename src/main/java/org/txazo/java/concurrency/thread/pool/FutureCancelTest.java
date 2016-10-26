package org.txazo.java.concurrency.thread.pool;

import java.io.IOException;
import java.util.concurrent.*;

public class FutureCancelTest {

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Future future = pool.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10000000; i++) {
                        Thread.sleep(1);
                        System.out.println(i + ": " + i * 12345678987654321L * 12345678987654321L / 98765432123456789L);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        try {
            future.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            boolean canceled = future.cancel(true);
            System.out.println("canceled: " + canceled);
        }

        System.in.read();
    }

}
