package org.txazo.cache.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    @Test
    public void test() {
        System.out.println("blpop before");
        jedis.blpop(10, "test");
        System.out.println("blpop after");
    }

}
