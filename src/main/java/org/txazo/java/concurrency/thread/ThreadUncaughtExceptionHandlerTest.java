package org.txazo.java.concurrency.thread;

import org.junit.Test;

/**
 * 线程非受检异常
 */
public class ThreadUncaughtExceptionHandlerTest {

    @Test
    public void test() throws InterruptedException {
        Thread t = new Thread("uncaught") {

            @Override
            public void run() {
                throw new RuntimeException();
            }

        };
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.print("Exception in thread \"" + t.getName() + "\" ");
                e.printStackTrace();
            }

        });
        t.start();
        t.join();
    }

}
