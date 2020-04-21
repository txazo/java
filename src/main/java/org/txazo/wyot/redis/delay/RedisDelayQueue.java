package org.txazo.wyot.redis.delay;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.txazo.wyot.jedis.RedisClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Redis延迟队列
 */
@Component
public class RedisDelayQueue implements DelayQueue<Message> {

    private static final int ADD_EXPIRE = 10 * 60;
    private static final String SCRIPT_ZSET_POLL =
            "local taskList = redis.pcall('ZRANGEBYSCORE', KEYS[1], ARGV[1], ARGV[2], 'limit', ARGV[3], ARGV[4])" +
                    " for k,v in pairs(taskList) do" +
                    "   redis.call('ZINCRBY', KEYS[1], ARGV[5], v)" +
                    " end" +
                    " return taskList";
    private static final String SCRIPT_SET_EXPIRE =
            "redis.call('SET', KEYS[1], ARGV[1]);" +
                    " redis.call('EXPIRE', KEYS[1], ARGV[2]);" +
                    " return 1";

    private String queueKey = "Post_Office_Delay_Queue";

    @Autowired
    public RedisClient redisClient;

    @Override
    public void put(DelayMessage<Message> delayMessage) {
        long delay = delayMessage.getNextTime() - DelayUtil.currentTime();
        int expire = (int) (Math.max(0, delay) + ADD_EXPIRE);

        redisClient.zadd(queueKey, delayMessage.getNextTime(), delayMessage.getKey());
        redisClient.eval(SCRIPT_SET_EXPIRE, Collections.singletonList(delayMessage.getKey()),
                Arrays.asList(serialize(delayMessage), String.valueOf(expire)));
//        redisClient.set(delayMessage.getKey(), delayMessage);
//        redisClient.expire(delayMessage.getKey(), expire);
    }

    @Override
    public List<String> poll(int size) {
        return (List<String>) redisClient.eval(SCRIPT_ZSET_POLL, Collections.singletonList(queueKey),
                Arrays.asList("0", String.valueOf(DelayUtil.currentTime()), "0", String.valueOf(size), "20"));
    }

    @Override
    public void delete(List<String> members) {
        if (CollectionUtils.isNotEmpty(members)) {
            redisClient.zrem(queueKey, members.toArray(new String[]{}));
        }
    }

    @Override
    public List<DelayMessage<Message>> gets(List<String> keys) {
        List<DelayMessage<Message>> messages = new ArrayList<>();
        for (String key : keys) {
            String value = redisClient.get(key);
            if (value != null) {
                messages.add((DelayMessage<Message>) JSON.parseObject(value, DelayMessage.class));
            }
        }
        return messages;
    }

    protected <V> String serialize(V v) {
        if (v instanceof String) {
            return (String) v;
        }
        return JSON.toJSONString(v);
    }

}
