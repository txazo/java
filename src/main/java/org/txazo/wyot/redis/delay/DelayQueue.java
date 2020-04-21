package org.txazo.wyot.redis.delay;

import java.util.List;

/**
 * 延迟队列
 */
public interface DelayQueue<V> {

    void put(DelayMessage<V> e);

    List<String> poll(int size);

    void delete(List<String> members);

    List<DelayMessage<V>> gets(List<String> keys);

}
