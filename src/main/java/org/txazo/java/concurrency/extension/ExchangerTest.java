package org.txazo.java.concurrency.extension;

import org.junit.Test;

import java.util.concurrent.Exchanger;

/**
 * Exchanger
 *
 * @see Exchanger
 * @see Exchanger#exchange(Object)
 */
public class ExchangerTest {

    @Test
    public void test() throws InterruptedException {
        final Exchanger<String> exchanger = new Exchanger<String>();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String result = exchanger.exchange("First");
                    System.out.println("Thread A exchanged return " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String result = exchanger.exchange("Second");
                    System.out.println("Thread B exchanged return " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        Thread.sleep(2000);
    }

}
