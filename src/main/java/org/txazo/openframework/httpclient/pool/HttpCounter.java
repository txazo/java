package org.txazo.openframework.httpclient.pool;

import java.util.concurrent.atomic.AtomicInteger;

public class HttpCounter {

    private final AtomicInteger total = new AtomicInteger(0);
    private final AtomicInteger current = new AtomicInteger(0);

    public void increment() {
        total.incrementAndGet();
        current.incrementAndGet();
    }

    public void decrement() {
        current.decrementAndGet();
    }

    public void waitUtilFinished() {
        while (true) {
            int count = current.get();
            HttpLogger.getLogger().info("[HttpClient] counter [current: " + count + ", total: " + total.get() + "]");
            if (count == 0) {
                HttpLogger.getLogger().info("[HttpClient] all requests finished");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                HttpLogger.getLogger().error("[HttpClient] sleep interrupted", e);
            }
        }
    }

}
