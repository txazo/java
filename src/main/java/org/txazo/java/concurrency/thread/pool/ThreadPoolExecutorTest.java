package org.txazo.java.concurrency.thread.pool;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {

    /**
     * 工作线程数 < corePoolSize, 新建工作线程
     */
    @Test
    public void test1() throws Exception {
        testThreadPool(100, 20, 100, 1000, new ThreadPoolExecutor(20, 20, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100)));
    }

    /**
     * 工作线程数 > corePoolSize, 工作队列未满, 添加到工作队列
     */
    @Test
    public void test2() throws Exception {
        testThreadPool(100, 50, 100, 5000, new ThreadPoolExecutor(20, 20, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100)));
    }

    /**
     * 工作线程数 > corePoolSize, 工作队列满, 新建工作线程
     */
    @Test
    public void test3() throws Exception {
        testThreadPool(100, 50, 100, 5000, new ThreadPoolExecutor(20, 40, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(20)));
    }

    /**
     * 工作队列满, woker工作线程数 > maximumPoolSize, reject策略处理
     */
    @Test
    public void test4() throws Exception {
        testThreadPool(1000, 20, 1000, 20000, new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                System.out.println(getTime() + " " + r.toString() + " rejected");
            }

        }));
    }

    private static void testThreadPool(int monitorIntervalTime, int threadCount, int threadIntervalTime, final int threadSleepTime, ThreadPoolExecutor threadPool) throws Exception {
        new Thread(new ThreadPoolExecutorMonitor(monitorIntervalTime, threadPool)).start();

        for (int i = 0; i < threadCount; i++) {
            threadPool.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        if (threadSleepTime > 0) {
                            Thread.sleep(threadSleepTime);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });

            if (threadIntervalTime > 0) {
                Thread.sleep(threadIntervalTime);
            }
        }

        System.in.read();
    }

    private static String getTime() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

    private static class ThreadPoolExecutorMonitor implements Runnable {

        private static long ctlOffset;
        private static long corePoolSizeOffset;
        private static long maximumPoolSizeOffset;
        private static long workQueueOffset;

        private final int monitorIntervalTime;
        private final ThreadPoolExecutor threadPool;
        private int corePoolSize;
        private int maximumPoolSize;

        static {
            try {
                ctlOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("ctl"));
                corePoolSizeOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("corePoolSize"));
                maximumPoolSizeOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("maximumPoolSize"));
                workQueueOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("workQueue"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        public ThreadPoolExecutorMonitor(int monitorIntervalTime, ThreadPoolExecutor threadPool) {
            this.monitorIntervalTime = monitorIntervalTime;
            this.threadPool = threadPool;
            corePoolSize = UnsafeHolder.unsafe.getInt(threadPool, corePoolSizeOffset);
            maximumPoolSize = UnsafeHolder.unsafe.getInt(threadPool, maximumPoolSizeOffset);
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("[" + getTime() + "]\tcorePoolSize " + corePoolSize + "\tmaximumPoolSize " + maximumPoolSize + "\tworkerCount " + getWorkerCount() + "\tworkQueueSize " + getWorkQueueSize());
                try {
                    Thread.sleep(monitorIntervalTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private int getWorkerCount() {
            AtomicInteger ctl = (AtomicInteger) UnsafeHolder.unsafe.getObject(threadPool, ctlOffset);
            return ctl.get() & ((1 << (Integer.SIZE - 3)) - 1);
        }

        private int getWorkQueueSize() {
            BlockingQueue<Runnable> workQueue = (BlockingQueue<Runnable>) UnsafeHolder.unsafe.getObject(threadPool, workQueueOffset);
            return workQueue.size();
        }

    }

    private static class UnsafeHolder {

        public static final Unsafe unsafe;

        static {
            try {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                unsafe = (Unsafe) field.get(null);
            } catch (Exception e) {
                throw new Error(e);
            }
        }

    }

}
