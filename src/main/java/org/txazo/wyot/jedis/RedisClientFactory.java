package org.txazo.wyot.jedis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Component
class RedisClientFactory implements InitializingBean {

    private JedisCluster jedisCluster;

    @Value("${redis.maxTotal:500}")
    private int maxTotal;

    @Value("${redis.maxIdle:30}")
    private int maxIdle;

    @Value("${redis.minIdle:5}")
    private int minIdle;

    @Value("${redis.connectionTimeout:5000}")
    private int connectionTimeout;

    @Value("${redis.soTimeout:3000}")
    private int soTimeout;

    @Value("${redis.maxAttempts:5}")
    private int maxAttempts;


    @Value("${redis.cluster.nodes:}")
    private String nodes;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);

        if (StringUtils.isBlank(nodes)) {
            return;
        }

        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        String[] nodeList = nodes.split("(?:\\s|,)+");
        for (String node : nodeList) {
            String[] nodeArr = node.split(":");
            jedisClusterNode.add(new HostAndPort(nodeArr[0], Integer.valueOf(nodeArr[1])));
        }

        jedisCluster = new JedisCluster(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, poolConfig);
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

}
