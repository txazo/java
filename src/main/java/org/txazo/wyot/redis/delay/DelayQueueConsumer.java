package org.txazo.wyot.redis.delay;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DelayQueueConsumer implements Runnable, InitializingBean {

    private static final ScheduledExecutorService SHARED_POOL = Executors.newScheduledThreadPool(5);

    @Value("${message.delayQueue.topic:post_office_delay_queue}")
    private String delayQueueTopic;

    @Value("${message.delayQueue.pollSize:20}")
    private int pollSize;

    @Autowired
    private RedisDelayQueue delayQueue;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        SHARED_POOL.scheduleWithFixedDelay(this, 1000, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        List<String> keys = null;
        while (CollectionUtils.isNotEmpty(keys = delayQueue.poll(pollSize))) {
            for (String key : keys) {
                System.out.println("Process key=" + key);
            }
            delayQueue.delete(keys);
        }
    }

}
