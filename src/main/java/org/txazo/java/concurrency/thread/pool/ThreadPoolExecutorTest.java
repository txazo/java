package org.txazo.java.concurrency.thread.pool;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
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

    @Test
    public void test() throws Exception {
        testThreadPool(1000, 20, 1000, 20000, new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                System.out.println("[" + getTime() + "] " + r.toString() + " rejected");
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
                        Thread.sleep(threadSleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });

            Thread.sleep(threadIntervalTime);
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
        private static long workersOffset;
        private static long workQueueOffset;
        private static long completedTaskCountOffset;

        private final int monitorIntervalTime;
        private final ThreadPoolExecutor threadPool;
        private int corePoolSize;
        private int maximumPoolSize;

        static {
            try {
                ctlOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("ctl"));
                corePoolSizeOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("corePoolSize"));
                maximumPoolSizeOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("maximumPoolSize"));
                workersOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("workers"));
                workQueueOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("workQueue"));
                completedTaskCountOffset = UnsafeHolder.unsafe.objectFieldOffset(ThreadPoolExecutor.class.getDeclaredField("completedTaskCount"));
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
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    int activeWorkerCount = 0;
                    int completedTaskCount = UnsafeHolder.unsafe.getInt(threadPool, completedTaskCountOffset);
                    Set<?> workers = (Set<?>) UnsafeHolder.unsafe.getObject(threadPool, workersOffset);
                    for (Object worker : workers) {
                        Field field = worker.getClass().getDeclaredField("completedTasks");
                        field.setAccessible(true);
                        completedTaskCount += field.getLong(worker);

                        Method method = worker.getClass().getDeclaredMethod("isLocked");
                        method.setAccessible(true);
                        if ((Boolean) method.invoke(worker)) {
                            activeWorkerCount++;
                        }
                    }

                    StringBuilder out = new StringBuilder();
                    out.append("[").append(getTime()).append("] corePoolSize ");
                    out.append(fillBlank(corePoolSize));
                    out.append(" maximumPoolSize ");
                    out.append(fillBlank(maximumPoolSize));
                    out.append(" workerCount ");
                    out.append(fillBlank(getWorkerCount()));
                    out.append(" activeWorkerCount ");
                    out.append(fillBlank(activeWorkerCount));
                    out.append(" workQueueSize ");
                    out.append(fillBlank(getWorkQueueSize()));
                    out.append(" completedTaskCount ");
                    out.append(fillBlank(completedTaskCount));

                    System.out.println(out.toString());

                    Thread.sleep(monitorIntervalTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private static String fillBlank(int i) {
            return i < 10 ? " " + i : String.valueOf(i);
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
