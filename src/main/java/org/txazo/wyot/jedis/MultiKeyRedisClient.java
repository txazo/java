package org.txazo.wyot.jedis;

import redis.clients.jedis.*;

import java.util.List;
import java.util.Set;

abstract class MultiKeyRedisClient extends ScriptingRedisClient {

    @Override
    public Long exists(String... keys) {
        return execute(new RedisCommand<Long>("exists", keys) {

            @Override
            public Long execute() {
                return getJedisCluster().exists(keys);
            }

        });
    }

    @Override
    public Long del(String... keys) {
        return execute(new RedisCommand<Long>("del", keys) {

            @Override
            public Long execute() {
                return getJedisCluster().del(keys);
            }

        });
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return execute(new RedisCommand<List<String>>("blpop", timeout, keys) {

            @Override
            public List<String> execute() {
                return getJedisCluster().blpop(timeout, keys);
            }

        });
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return execute(new RedisCommand<List<String>>("brpop", timeout, keys) {

            @Override
            public List<String> execute() {
                return getJedisCluster().brpop(timeout, keys);
            }

        });
    }

    @Override
    public List<String> mget(String... keys) {
        return execute(new RedisCommand<List<String>>("mget", keys) {

            @Override
            public List<String> execute() {
                return getJedisCluster().mget(keys);
            }

        });
    }

    @Override
    public String mset(String... keysvalues) {
        return execute(new RedisCommand<String>("mset", keysvalues) {

            @Override
            public String execute() {
                return getJedisCluster().mset(keysvalues);
            }

        });
    }

    @Override
    public Long msetnx(String... keysvalues) {
        return execute(new RedisCommand<Long>("msetnx", keysvalues) {

            @Override
            public Long execute() {
                return getJedisCluster().msetnx(keysvalues);
            }

        });
    }

    @Override
    public String rename(String oldkey, String newkey) {
        return execute(new RedisCommand<String>("rename", oldkey, newkey) {

            @Override
            public String execute() {
                return getJedisCluster().rename(oldkey, newkey);
            }

        });
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        return execute(new RedisCommand<Long>("renamenx", oldkey, newkey) {

            @Override
            public Long execute() {
                return getJedisCluster().renamenx(oldkey, newkey);
            }

        });
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return execute(new RedisCommand<String>("rpoplpush", srckey, dstkey) {

            @Override
            public String execute() {
                return getJedisCluster().rpoplpush(srckey, dstkey);
            }

        });
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return execute(new RedisCommand<Set<String>>("sdiff", keys) {

            @Override
            public Set<String> execute() {
                return getJedisCluster().sdiff(keys);
            }

        });
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return execute(new RedisCommand<Long>("sdiffstore", dstkey, keys) {

            @Override
            public Long execute() {
                return getJedisCluster().sdiffstore(dstkey, keys);
            }

        });
    }

    @Override
    public Set<String> sinter(String... keys) {
        return execute(new RedisCommand<Set<String>>("sinter", keys) {

            @Override
            public Set<String> execute() {
                return getJedisCluster().sinter(keys);
            }

        });
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return execute(new RedisCommand<Long>("sinterstore", dstkey, keys) {

            @Override
            public Long execute() {
                return getJedisCluster().sinterstore(dstkey, keys);
            }

        });
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        return execute(new RedisCommand<Long>("smove", srckey, dstkey, member) {

            @Override
            public Long execute() {
                return getJedisCluster().smove(srckey, dstkey, member);
            }

        });
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return execute(new RedisCommand<Long>("sort", key, sortingParameters, dstkey) {

            @Override
            public Long execute() {
                return getJedisCluster().sort(key, sortingParameters, dstkey);
            }

        });
    }

    @Override
    public Long sort(String key, String dstkey) {
        return execute(new RedisCommand<Long>("sort", key, dstkey) {

            @Override
            public Long execute() {
                return getJedisCluster().sort(key, dstkey);
            }

        });
    }

    @Override
    public Set<String> sunion(String... keys) {
        return execute(new RedisCommand<Set<String>>("sunion", keys) {

            @Override
            public Set<String> execute() {
                return getJedisCluster().sunion(keys);
            }

        });
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return execute(new RedisCommand<Long>("sunionstore", dstkey, keys) {

            @Override
            public Long execute() {
                return getJedisCluster().sunionstore(dstkey, keys);
            }

        });
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return execute(new RedisCommand<Long>("zinterstore", dstkey, sets) {

            @Override
            public Long execute() {
                return getJedisCluster().zinterstore(dstkey, sets);
            }

        });
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return execute(new RedisCommand<Long>("zinterstore", dstkey, params, sets) {

            @Override
            public Long execute() {
                return getJedisCluster().zinterstore(dstkey, params, sets);
            }

        });
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return execute(new RedisCommand<Long>("zunionstore", dstkey, sets) {

            @Override
            public Long execute() {
                return getJedisCluster().zunionstore(dstkey, sets);
            }

        });
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return execute(new RedisCommand<Long>("zunionstore", dstkey, params, sets) {

            @Override
            public Long execute() {
                return getJedisCluster().zunionstore(dstkey, params, sets);
            }

        });
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return execute(new RedisCommand<String>("brpoplpush", source, destination, timeout) {

            @Override
            public String execute() {
                return getJedisCluster().brpoplpush(source, destination, timeout);
            }

        });
    }

    @Override
    public Long publish(String channel, String message) {
        return execute(new RedisCommand<Long>("publish", channel, message) {

            @Override
            public Long execute() {
                return getJedisCluster().publish(channel, message);
            }

        });
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        getJedisCluster().subscribe(jedisPubSub, channels);
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        getJedisCluster().psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Long bitop(BitOP op, String destKey, String... srcKeys) {
        return execute(new RedisCommand<Long>("bitop", op, destKey, srcKeys) {

            @Override
            public Long execute() {
                return getJedisCluster().bitop(op, destKey, srcKeys);
            }

        });
    }

    @Override
    public String pfmerge(String destkey, String... sourcekeys) {
        return execute(new RedisCommand<String>("pfmerge", destkey, sourcekeys) {

            @Override
            public String execute() {
                return getJedisCluster().pfmerge(destkey, sourcekeys);
            }

        });
    }

    @Override
    public long pfcount(String... keys) {
        return execute(new RedisCommand<Long>("pfcount", keys) {

            @Override
            public Long execute() {
                return getJedisCluster().pfcount(keys);
            }

        });
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        return execute(new RedisCommand<ScanResult<String>>("scan", cursor, params) {

            @Override
            public ScanResult<String> execute() {
                return getJedisCluster().scan(cursor, params);
            }

        });
    }

}
