package org.txazo.wyot.redis.delay;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 延迟消息
 */
@Data
@NoArgsConstructor
public class DelayMessage<V> implements Serializable {

    private static final long serialVersionUID = 3029281555423371721L;

    private String key;

    private Message message;

    // 下一次发送时间
    private long nextTime;

    // 已发送成功次数
    private int successedTimes;

    // 已发送失败次数
    private int failedTimes;

    public DelayMessage(String key, Message message, long nextTime) {
        this.key = key;
        this.message = message;
        this.nextTime = nextTime;
    }

    public void incrSuccessedTimes() {
        successedTimes++;
    }

    public void incrFailedTimes() {
        failedTimes++;
    }

}
