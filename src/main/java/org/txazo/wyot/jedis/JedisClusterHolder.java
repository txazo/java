package org.txazo.wyot.jedis;

import redis.clients.jedis.JedisCluster;

public interface JedisClusterHolder {

    JedisCluster getJedisCluster();

}
