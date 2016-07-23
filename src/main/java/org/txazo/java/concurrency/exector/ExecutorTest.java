package org.txazo.java.concurrency.exector;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @see Executor
 */
public class ExecutorTest {

    @Test
    public void testExecutor() {
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("Thread run");
            }

        });
    }

    private static class DirectExecutor implements Executor {

        @Override
        public void execute(Runnable r) {
            r.run();
        }

    }

    private static class ThreadExecutor implements Executor {

        @Override
        public void execute(Runnable r) {
            new Thread(r).start();
        }

    }

}
