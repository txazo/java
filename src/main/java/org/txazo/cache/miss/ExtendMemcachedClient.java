package org.txazo.cache.miss;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExtendMemcachedClient extends MemcachedClient {

    private DelayQueue<KeyDelayed> overdueQueues = new DelayQueue<KeyDelayed>();
    private ConcurrentHashMap<String, ItemBlock> itemBlocks = new ConcurrentHashMap<String, ItemBlock>();

    public ExtendMemcachedClient(InetSocketAddress... ia) throws IOException {
        super(ia);
        new Thread(new ClearKeyThread()).start();
    }

    public Object getAndSet(int i, String key, Callable callable) {
        ItemBlock itemBlock = new ItemBlock(key);
        ItemBlock oldBlock = itemBlocks.putIfAbsent(key, new ItemBlock(key));
        if (oldBlock != null) {
            itemBlock = oldBlock;
        }
        return itemBlock.get(i, callable);
    }

    protected Object getFromCache(String key) {
        return super.get(key);
    }

    protected void set(String key, Object value, int expire) {
        super.set(key, expire, value);
    }

    protected Object gainCacheUpdateValue(String key, Callable callable) throws Exception {
        return callable.call();
    }

    private class ClearKeyThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    itemBlocks.remove(overdueQueues.take().getKey());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * key锁
     */
    private class ItemBlock {

        // key
        private String key;
        // 是否阻塞
        private volatile AtomicBoolean blocked = new AtomicBoolean(false);
        // 是否结束
        private volatile boolean end = false;

        public ItemBlock(String key) {
            this.key = key;
        }

        public Object get(int i, Callable callable) {
            if (end) {
                // 锁结束, 从缓存查询
                return getFromCache(key);
            }
            // 锁竞争
            if (!end && blocked.compareAndSet(false, true)) {
                // 获取到锁, 查询数据库
                System.out.println("get from db");
                try {
                    Object value = gainCacheUpdateValue(key, callable);
                    set(key, value, 3);
                    // 设置结束标识
                    end = true;
                    // 唤醒所有等待锁的线程
                    notifyAllBlock();
                    // key添加到过期队列
                    overdueQueues.offer(new KeyDelayed(key));
                    return value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            } else {
                // 未获取到锁
                try {
                    if (end) {
                        // 结束, 缓存填充完成, 查询缓存
                        return getFromCache(key);
                    }
                    System.out.println("get wait");
                    // 等待
                    waitBlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 锁释放, 查询缓存
                return getFromCache(key);
            }
        }

        private synchronized void waitBlock() throws InterruptedException {
            wait();
        }

        private synchronized void notifyAllBlock() throws InterruptedException {
            notifyAll();
        }

    }

    /**
     * 过期key
     */
    private class KeyDelayed implements Delayed {

        private static final int DELAY_MILLIS = 1000;

        private String key;
        private long delayTime;

        public KeyDelayed(String key) {
            this.key = key;
            this.delayTime = System.currentTimeMillis() + DELAY_MILLIS;
        }

        public String getKey() {
            return key;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            KeyDelayed that = (KeyDelayed) o;
            return this.delayTime > that.delayTime ? 1 : this.delayTime < that.delayTime ? -1 : 0;
        }

    }

}
