package org.txazo.wyot.redis.delay;

public abstract class DelayUtil {

    public static long currentTime() {
        return (long) Math.floor(System.currentTimeMillis() / 1000);
    }

    public static boolean isDelayTime(long time) {
        return time > currentTime();
    }

}
