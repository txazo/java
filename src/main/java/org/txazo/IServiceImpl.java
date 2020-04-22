package org.txazo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class IServiceImpl {

    private LoadingCache<Integer, Stats> statsCache = CacheBuilder
            .newBuilder()
            .expireAfterAccess(15, TimeUnit.SECONDS)
            .expireAfterWrite(15, TimeUnit.SECONDS)
            .maximumSize(100)
            .build(new CacheLoader<Integer, Stats>() {

                @Override
                public Stats load(Integer integer) throws Exception {
                    return new Stats(integer);
                }

            });

    public static void main(String[] args) throws Exception {
        IServiceImpl iService = new IServiceImpl();
        for (int i = 0; i < 1000; i++) {
            iService.test();
            Thread.sleep(RandomUtils.nextInt(100, 200));
        }
    }

    public void test() {
        int currSecode = (int) (System.currentTimeMillis() / 1000);
        int status = call();
        try {
            printPrevStats(currSecode);
            Stats stats = statsCache.get(currSecode);
            if (status == 200) {
                stats.getSuccess().incrementAndGet();
            } else {
                stats.getFail().incrementAndGet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printPrevStats(int currSecode) throws Exception {
        int total = 0;
        int fail = 0;
        for (int i = currSecode - 1; i > currSecode - 11; i--) {
            Stats stats = statsCache.get(i);
            total += stats.getSuccess().get() + stats.getFail().get();
            fail += stats.getFail().get();
        }
        System.out.println(String.format("Last 10 seconds, total=%d, fail=%d, failRate=%d", total, fail,
                total == 0 ? 0 : fail * 100 / total));
    }

    private int call() {
        return RandomUtils.nextInt(0, 2) == 0 ? 200 : 500;
    }

    private static class Stats {

        private int second;
        private AtomicInteger success = new AtomicInteger(0);
        private AtomicInteger fail = new AtomicInteger(0);

        public Stats() {
        }

        public Stats(int second) {
            this.second = second;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public AtomicInteger getSuccess() {
            return success;
        }

        public AtomicInteger getFail() {
            return fail;
        }
    }

}
