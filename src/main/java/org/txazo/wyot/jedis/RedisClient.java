package org.txazo.wyot.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RedisClient extends RedisEntityClient {

    @Autowired
    private RedisClientFactory redisClientFactory;

    @Override
    public JedisCluster getJedisCluster() {
        return redisClientFactory.getJedisCluster();
    }

    @Override
    public String set(String key, String value) {
        return execute(new RedisCommand<String>("set", key, value) {

            @Override
            public String execute() {
                return getJedisCluster().set(key, value);
            }

        });
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        return execute(new RedisCommand<String>("set", key, value, nxxx, expx, time) {

            @Override
            public String execute() {
                return getJedisCluster().set(key, value, nxxx, expx, time);
            }

        });
    }

    @Override
    public String set(String key, String value, String nxxx) {
        return execute(new RedisCommand<String>("set", key, value, nxxx) {

            @Override
            String execute() {
                return getJedisCluster().set(key, value, nxxx);
            }

        });
    }

    @Override
    public String get(String key) {
        return execute(new RedisCommand<String>("get", key) {

            @Override
            String execute() {
                return getJedisCluster().get(key);
            }

        });
    }

    @Override
    public Boolean exists(String key) {
        return execute(new RedisCommand<Boolean>("exists", key) {

            @Override
            Boolean execute() {
                return getJedisCluster().exists(key);
            }

        });
    }

    @Override
    public Long persist(String key) {
        return execute(new RedisCommand<Long>("persist", key) {

            @Override
            Long execute() {
                return getJedisCluster().persist(key);
            }

        });
    }

    @Override
    public String type(String key) {
        return execute(new RedisCommand<String>("type", key) {

            @Override
            String execute() {
                return getJedisCluster().type(key);
            }

        });
    }

    @Override
    public Long expire(String key, int seconds) {
        return execute(new RedisCommand<Long>("expire", key, seconds) {

            @Override
            Long execute() {
                return getJedisCluster().expire(key, seconds);
            }

        });
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return execute(new RedisCommand<Long>("pexpire", key, milliseconds) {

            @Override
            Long execute() {
                return getJedisCluster().pexpire(key, milliseconds);
            }

        });
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return execute(new RedisCommand<Long>("expireAt", key, unixTime) {

            @Override
            Long execute() {
                return getJedisCluster().expireAt(key, unixTime);
            }

        });
    }

    @Override
    public Long pexpireAt(String key, long millisecondsTimestamp) {
        return execute(new RedisCommand<Long>("pexpireAt", key, millisecondsTimestamp) {

            @Override
            Long execute() {
                return getJedisCluster().pexpireAt(key, millisecondsTimestamp);
            }

        });
    }

    @Override
    public Long ttl(String key) {
        return execute(new RedisCommand<Long>("ttl", key) {

            @Override
            Long execute() {
                return getJedisCluster().ttl(key);
            }

        });
    }

    @Override
    public Long pttl(String key) {
        return execute(new RedisCommand<Long>("pttl", key) {

            @Override
            Long execute() {
                return getJedisCluster().pttl(key);
            }

        });
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return execute(new RedisCommand<Boolean>("setbit", key, offset, value) {

            @Override
            Boolean execute() {
                return getJedisCluster().setbit(key, offset, value);
            }

        });
    }

    @Override
    public Boolean setbit(String key, long offset, String value) {
        return execute(new RedisCommand<Boolean>("setbit", key, offset, value) {

            @Override
            Boolean execute() {
                return getJedisCluster().setbit(key, offset, value);
            }

        });
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return execute(new RedisCommand<Boolean>("getbit", key, offset) {

            @Override
            Boolean execute() {
                return getJedisCluster().getbit(key, offset);
            }

        });
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return execute(new RedisCommand<Long>("setrange", key, offset, value) {

            @Override
            Long execute() {
                return getJedisCluster().setrange(key, offset, value);
            }

        });
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return execute(new RedisCommand<String>("getrange", key, startOffset, endOffset) {

            @Override
            String execute() {
                return getJedisCluster().getrange(key, startOffset, endOffset);
            }

        });
    }

    @Override
    public String getSet(String key, String value) {
        return execute(new RedisCommand<String>("getSet", key, value) {

            @Override
            String execute() {
                return getJedisCluster().getSet(key, value);
            }

        });
    }

    @Override
    public Long setnx(String key, String value) {
        return execute(new RedisCommand<Long>("setnx", key, value) {

            @Override
            Long execute() {
                return getJedisCluster().setnx(key, value);
            }

        });
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return execute(new RedisCommand<String>("setex", key, seconds, value) {

            @Override
            String execute() {
                return getJedisCluster().setex(key, seconds, value);
            }

        });
    }

    @Override
    public String psetex(String key, long milliseconds, String value) {
        return execute(new RedisCommand<String>("psetex", key, milliseconds, value) {

            @Override
            String execute() {
                return getJedisCluster().psetex(key, milliseconds, value);
            }

        });
    }

    @Override
    public Long decrBy(String key, long integer) {
        return execute(new RedisCommand<Long>("decrBy", key, integer) {

            @Override
            Long execute() {
                return getJedisCluster().decrBy(key, integer);
            }

        });
    }

    @Override
    public Long decr(String key) {
        return execute(new RedisCommand<Long>("decr", key) {

            @Override
            Long execute() {
                return getJedisCluster().decr(key);
            }

        });
    }

    @Override
    public Long incrBy(String key, long integer) {
        return execute(new RedisCommand<Long>("incrBy", key, integer) {

            @Override
            Long execute() {
                return getJedisCluster().incrBy(key, integer);
            }

        });
    }

    @Override
    public Double incrByFloat(String key, double value) {
        return execute(new RedisCommand<Double>("incrByFloat", key, value) {

            @Override
            Double execute() {
                return getJedisCluster().incrByFloat(key, value);
            }

        });
    }

    @Override
    public Long incr(String key) {
        return execute(new RedisCommand<Long>("incr", key) {

            @Override
            Long execute() {
                return getJedisCluster().incr(key);
            }

        });
    }

    @Override
    public Long append(String key, String value) {
        return execute(new RedisCommand<Long>("append", key, value) {

            @Override
            Long execute() {
                return getJedisCluster().append(key, value);
            }

        });
    }

    @Override
    public String substr(String key, int start, int end) {
        return execute(new RedisCommand<String>("substr", key, start, end) {

            @Override
            String execute() {
                return getJedisCluster().substr(key, start, end);
            }

        });
    }

    @Override
    public Long hset(String key, String field, String value) {
        return execute(new RedisCommand<Long>("hset", key, field, value) {

            @Override
            Long execute() {
                return getJedisCluster().hset(key, field, value);
            }

        });
    }

    @Override
    public String hget(String key, String field) {
        return execute(new RedisCommand<String>("hget", key, field) {

            @Override
            String execute() {
                return getJedisCluster().hget(key, field);
            }

        });
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return execute(new RedisCommand<Long>("hsetnx", key, field, value) {

            @Override
            Long execute() {
                return getJedisCluster().hsetnx(key, field, value);
            }

        });
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return execute(new RedisCommand<String>("hmset", key, hash) {

            @Override
            String execute() {
                return getJedisCluster().hmset(key, hash);
            }

        });
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return execute(new RedisCommand<List<String>>("hmget", key, fields) {

            @Override
            List<String> execute() {
                return getJedisCluster().hmget(key, fields);
            }

        });
    }


    @Override
    public Long hincrBy(String key, String field, long value) {
        return execute(new RedisCommand<Long>("hincrBy", key, field, value) {

            @Override
            Long execute() {
                return getJedisCluster().hincrBy(key, field, value);
            }

        });
    }

    @Override
    public Double hincrByFloat(String key, String field, double value) {
        return execute(new RedisCommand<Double>("hincrByFloat", key, field, value) {

            @Override
            Double execute() {
                return getJedisCluster().hincrByFloat(key, field, value);
            }

        });
    }

    @Override
    public Boolean hexists(String key, String field) {
        return execute(new RedisCommand<Boolean>("hexists", key, field) {

            @Override
            Boolean execute() {
                return getJedisCluster().hexists(key, field);
            }

        });
    }

    @Override
    public Long hdel(String key, String... field) {
        return execute(new RedisCommand<Long>("hdel", key, field) {

            @Override
            Long execute() {
                return getJedisCluster().hdel(key, field);
            }

        });
    }

    @Override
    public Long hlen(String key) {
        return execute(new RedisCommand<Long>("hlen", key) {

            @Override
            Long execute() {
                return getJedisCluster().hlen(key);
            }

        });
    }

    @Override
    public Set<String> hkeys(String key) {
        return execute(new RedisCommand<Set<String>>("hkeys", key) {

            @Override
            Set<String> execute() {
                return getJedisCluster().hkeys(key);
            }

        });
    }

    @Override
    public List<String> hvals(String key) {
        return execute(new RedisCommand<List<String>>("hvals", key) {

            @Override
            List<String> execute() {
                return getJedisCluster().hvals(key);
            }

        });
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return execute(new RedisCommand<Map<String, String>>("hgetAll", key) {

            @Override
            Map<String, String> execute() {
                return getJedisCluster().hgetAll(key);
            }

        });
    }

    @Override
    public Long rpush(String key, String... string) {
        return execute(new RedisCommand<Long>("rpush", key, string) {

            @Override
            Long execute() {
                return getJedisCluster().rpush(key, string);
            }

        });
    }

    @Override
    public Long lpush(String key, String... string) {
        return execute(new RedisCommand<Long>("lpush", key, string) {

            @Override
            Long execute() {
                return getJedisCluster().lpush(key, string);
            }

        });
    }

    @Override
    public Long llen(String key) {
        return execute(new RedisCommand<Long>("llen", key) {

            @Override
            Long execute() {
                return getJedisCluster().llen(key);
            }

        });
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return execute(new RedisCommand<List<String>>("lrange", key, start, end) {

            @Override
            List<String> execute() {
                return getJedisCluster().lrange(key, start, end);
            }

        });
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return execute(new RedisCommand<String>("ltrim", key, start, end) {

            @Override
            String execute() {
                return getJedisCluster().ltrim(key, start, end);
            }

        });
    }

    @Override
    public String lindex(String key, long index) {
        return execute(new RedisCommand<String>("lindex", key, index) {

            @Override
            String execute() {
                return getJedisCluster().lindex(key, index);
            }

        });
    }

    @Override
    public String lset(String key, long index, String value) {
        return execute(new RedisCommand<String>("lset", key, index, value) {

            @Override
            String execute() {
                return getJedisCluster().lset(key, index, value);
            }

        });
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return execute(new RedisCommand<Long>("lrem", key, count, value) {

            @Override
            Long execute() {
                return getJedisCluster().lrem(key, count, value);
            }

        });
    }

    @Override
    public String lpop(String key) {
        return execute(new RedisCommand<String>("lpop", key) {

            @Override
            String execute() {
                return getJedisCluster().lpop(key);
            }

        });
    }

    @Override
    public String rpop(String key) {
        return execute(new RedisCommand<String>("rpop", key) {

            @Override
            String execute() {
                return getJedisCluster().rpop(key);
            }

        });
    }

    @Override
    public Long sadd(String key, String... member) {
        return execute(new RedisCommand<Long>("sadd", key, member) {

            @Override
            Long execute() {
                return getJedisCluster().sadd(key, member);
            }

        });
    }

    @Override
    public Set<String> smembers(String key) {
        return execute(new RedisCommand<Set<String>>("smembers", key) {

            @Override
            Set<String> execute() {
                return getJedisCluster().smembers(key);
            }

        });
    }

    @Override
    public Long srem(String key, String... member) {
        return execute(new RedisCommand<Long>("srem", key, member) {

            @Override
            Long execute() {
                return getJedisCluster().srem(key, member);
            }

        });
    }

    @Override
    public String spop(String key) {
        return execute(new RedisCommand<String>("spop", key) {

            @Override
            String execute() {
                return getJedisCluster().spop(key);
            }

        });
    }

    @Override
    public Set<String> spop(String key, long count) {
        return execute(new RedisCommand<Set<String>>("spop", key, count) {

            @Override
            Set<String> execute() {
                return getJedisCluster().spop(key, count);
            }

        });
    }

    @Override
    public Long scard(String key) {
        return execute(new RedisCommand<Long>("scard", key) {

            @Override
            Long execute() {
                return getJedisCluster().scard(key);
            }

        });
    }

    @Override
    public Boolean sismember(String key, String member) {
        return execute(new RedisCommand<Boolean>("sismember", key, member) {

            @Override
            Boolean execute() {
                return getJedisCluster().sismember(key, member);
            }

        });
    }

    @Override
    public String srandmember(String key) {
        return execute(new RedisCommand<String>("srandmember", key) {

            @Override
            String execute() {
                return getJedisCluster().srandmember(key);
            }

        });
    }

    @Override
    public List<String> srandmember(String key, int count) {
        return execute(new RedisCommand<List<String>>("srandmember", key, count) {

            @Override
            List<String> execute() {
                return getJedisCluster().srandmember(key, count);
            }

        });
    }

    @Override
    public Long strlen(String key) {
        return execute(new RedisCommand<Long>("strlen", key) {

            @Override
            Long execute() {
                return getJedisCluster().strlen(key);
            }

        });
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return execute(new RedisCommand<Long>("zadd", key, score, member) {

            @Override
            Long execute() {
                return getJedisCluster().zadd(key, score, member);
            }

        });
    }

    @Override
    public Long zadd(String key, double score, String member, ZAddParams params) {
        return execute(new RedisCommand<Long>("zadd", key, score, member, params) {

            @Override
            Long execute() {
                return getJedisCluster().zadd(key, score, member, params);
            }

        });
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        return execute(new RedisCommand<Long>("zadd", key, scoreMembers) {

            @Override
            Long execute() {
                return getJedisCluster().zadd(key, scoreMembers);
            }

        });
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
        return execute(new RedisCommand<Long>("zadd", key, scoreMembers, params) {

            @Override
            Long execute() {
                return getJedisCluster().zadd(key, scoreMembers, params);
            }

        });
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return execute(new RedisCommand<Set<String>>("zrange", key, start, end) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrange(key, start, end);
            }

        });
    }

    @Override
    public Long zrem(String key, String... member) {
        return execute(new RedisCommand<Long>("zrem", key, member) {

            @Override
            Long execute() {
                return getJedisCluster().zrem(key, member);
            }

        });
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return execute(new RedisCommand<Double>("zincrby", key, score, member) {

            @Override
            Double execute() {
                return getJedisCluster().zincrby(key, score, member);
            }

        });
    }

    @Override
    public Double zincrby(String key, double score, String member, ZIncrByParams params) {
        return execute(new RedisCommand<Double>("zincrby", key, score, member, params) {

            @Override
            Double execute() {
                return getJedisCluster().zincrby(key, score, member, params);
            }

        });
    }

    @Override
    public Long zrank(String key, String member) {
        return execute(new RedisCommand<Long>("zrank", key, member) {

            @Override
            Long execute() {
                return getJedisCluster().zrank(key, member);
            }

        });
    }

    @Override
    public Long zrevrank(String key, String member) {
        return execute(new RedisCommand<Long>("zrevrank", key, member) {

            @Override
            Long execute() {
                return getJedisCluster().zrevrank(key, member);
            }

        });
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return execute(new RedisCommand<Set<String>>("zrevrange", key, start, end) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrevrange(key, start, end);
            }

        });
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return execute(new RedisCommand<Set<Tuple>>("zrangeWithScores", key, start, end) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrangeWithScores(key, start, end);
            }

        });
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return execute(new RedisCommand<Set<Tuple>>("zrevrangeWithScores", key, start, end) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrevrangeWithScores(key, start, end);
            }

        });
    }

    @Override
    public Long zcard(String key) {
        return execute(new RedisCommand<Long>("zcard", key) {

            @Override
            Long execute() {
                return getJedisCluster().zcard(key);
            }

        });
    }

    @Override
    public Double zscore(String key, String member) {
        return execute(new RedisCommand<Double>("zscore", key, member) {

            @Override
            Double execute() {
                return getJedisCluster().zscore(key, member);
            }

        });
    }

    @Override
    public List<String> sort(String key) {
        return execute(new RedisCommand<List<String>>("sort", key) {

            @Override
            List<String> execute() {
                return getJedisCluster().sort(key);
            }

        });
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return execute(new RedisCommand<List<String>>("sort", key, sortingParameters) {

            @Override
            List<String> execute() {
                return getJedisCluster().sort(key, sortingParameters);
            }

        });
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return execute(new RedisCommand<Long>("zcount", key, min, max) {

            @Override
            Long execute() {
                return getJedisCluster().zcount(key, min, max);
            }

        });
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return execute(new RedisCommand<Long>("zcount", key, min, max) {

            @Override
            Long execute() {
                return getJedisCluster().zcount(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return execute(new RedisCommand<Set<String>>("zrangeByScore", key, min, max) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrangeByScore(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return execute(new RedisCommand<Set<String>>("zrangeByScore", key, min, max) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrangeByScore(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return execute(new RedisCommand<Set<String>>("zrevrangeByScore", key, min, max) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrevrangeByScore(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return execute(new RedisCommand<Set<String>>("zrangeByScore", key, min, max, offset, count) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrangeByScore(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return execute(new RedisCommand<Set<String>>("zrevrangeByScore", key, min, max) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrevrangeByScore(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return execute(new RedisCommand<Set<String>>("zrangeByScore", key, min, max, offset, count) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrangeByScore(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return execute(new RedisCommand<Set<String>>("zrevrangeByScore", key, min, max, offset, count) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrevrangeByScore(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return execute(new RedisCommand<Set<Tuple>>("zrangeByScoreWithScores", key, min, max) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrangeByScoreWithScores(key, min, max);
            }

        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return execute(new RedisCommand<Set<Tuple>>("zrevrangeByScoreWithScores", key, min, max) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrevrangeByScoreWithScores(key, min, max);
            }

        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return execute(new RedisCommand<Set<Tuple>>("zrangeByScoreWithScores", key, min, max, offset, count) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrangeByScoreWithScores(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return execute(new RedisCommand<Set<String>>("zrevrangeByScore", key, min, max, offset, count) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrevrangeByScore(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return execute(new RedisCommand<Set<Tuple>>("zrangeByScoreWithScores", key, min, max) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrangeByScoreWithScores(key, min, max);
            }

        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return execute(new RedisCommand<Set<Tuple>>("zrevrangeByScoreWithScores", key, min, max) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrevrangeByScoreWithScores(key, min, max);
            }

        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return execute(new RedisCommand<Set<Tuple>>("zrangeByScoreWithScores", key, min, max, offset, count) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrangeByScoreWithScores(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return execute(new RedisCommand<Set<Tuple>>("zrevrangeByScoreWithScores", key, min, max, offset, count) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrevrangeByScoreWithScores(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return execute(new RedisCommand<Set<Tuple>>("zrevrangeByScoreWithScores", key, min, max, offset, count) {

            @Override
            Set<Tuple> execute() {
                return getJedisCluster().zrevrangeByScoreWithScores(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return execute(new RedisCommand<Long>("zremrangeByRank", key, start, end) {

            @Override
            Long execute() {
                return getJedisCluster().zremrangeByRank(key, start, end);
            }

        });
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        return execute(new RedisCommand<Long>("zremrangeByScore", key, start, end) {

            @Override
            Long execute() {
                return getJedisCluster().zremrangeByScore(key, start, end);
            }

        });
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        return execute(new RedisCommand<Long>("zremrangeByScore", key, start, end) {

            @Override
            Long execute() {
                return getJedisCluster().zremrangeByScore(key, start, end);
            }

        });
    }

    @Override
    public Long zlexcount(String key, String min, String max) {
        return execute(new RedisCommand<Long>("zlexcount", key, min, max) {

            @Override
            Long execute() {
                return getJedisCluster().zlexcount(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        return execute(new RedisCommand<Set<String>>("zrangeByLex", key, min, max) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrangeByLex(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        return execute(new RedisCommand<Set<String>>("zrangeByLex", key, min, max, offset, count) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrangeByLex(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min) {
        return execute(new RedisCommand<Set<String>>("zrevrangeByLex", key, min, max) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrevrangeByLex(key, min, max);
            }

        });
    }

    @Override
    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        return execute(new RedisCommand<Set<String>>("zrevrangeByLex", key, min, max, offset, count) {

            @Override
            Set<String> execute() {
                return getJedisCluster().zrevrangeByLex(key, min, max, offset, count);
            }

        });
    }

    @Override
    public Long zremrangeByLex(String key, String min, String max) {
        return execute(new RedisCommand<Long>("zremrangeByLex", key, min, max) {

            @Override
            Long execute() {
                return getJedisCluster().zremrangeByLex(key, min, max);
            }

        });
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return execute(new RedisCommand<Long>("linsert", key, where, pivot, value) {

            @Override
            Long execute() {
                return getJedisCluster().linsert(key, where, pivot, value);
            }

        });
    }

    @Override
    public Long lpushx(String key, String... string) {
        return execute(new RedisCommand<Long>("lpushx", key, string) {

            @Override
            Long execute() {
                return getJedisCluster().lpushx(key, string);
            }

        });
    }

    @Override
    public Long rpushx(String key, String... string) {
        return execute(new RedisCommand<Long>("rpushx", key, string) {

            @Override
            Long execute() {
                return getJedisCluster().rpushx(key, string);
            }

        });
    }

    @Override
    public List<String> blpop(String arg) {
        return execute(new RedisCommand<List<String>>("blpop", arg) {

            @Override
            List<String> execute() {
                return getJedisCluster().blpop(arg);
            }

        });
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return execute(new RedisCommand<List<String>>("blpop", timeout, key) {

            @Override
            List<String> execute() {
                return getJedisCluster().blpop(timeout, key);
            }

        });
    }

    @Override
    public List<String> brpop(String arg) {
        return execute(new RedisCommand<List<String>>("brpop", arg) {

            @Override
            List<String> execute() {
                return getJedisCluster().brpop(arg);
            }

        });
    }

    @Override
    public List<String> brpop(int timeout, String key) {
        return execute(new RedisCommand<List<String>>("brpop", timeout, key) {

            @Override
            List<String> execute() {
                return getJedisCluster().brpop(timeout, key);
            }

        });
    }

    @Override
    public Long del(String key) {
        return execute(new RedisCommand<Long>("del", key) {

            @Override
            Long execute() {
                return getJedisCluster().del(key);
            }

        });
    }

    @Override
    public String echo(String string) {
        return execute(new RedisCommand<String>("echo", string) {

            @Override
            String execute() {
                return getJedisCluster().echo(string);
            }

        });
    }

    @Override
    public Long move(String key, int dbIndex) {
        return execute(new RedisCommand<Long>("move", key, dbIndex) {

            @Override
            Long execute() {
                return getJedisCluster().move(key, dbIndex);
            }

        });
    }

    @Override
    public Long bitcount(String key) {
        return execute(new RedisCommand<Long>("bitcount", key) {

            @Override
            Long execute() {
                return getJedisCluster().bitcount(key);
            }

        });
    }

    @Override
    public Long bitcount(String key, long start, long end) {
        return execute(new RedisCommand<Long>("bitcount", key, start, end) {

            @Override
            Long execute() {
                return getJedisCluster().bitcount(key, start, end);
            }

        });
    }

    @Override
    public Long bitpos(String key, boolean value) {
        return execute(new RedisCommand<Long>("bitpos", key, value) {

            @Override
            Long execute() {
                return getJedisCluster().bitpos(key, value);
            }

        });
    }

    @Override
    public Long bitpos(String key, boolean value, BitPosParams params) {
        return execute(new RedisCommand<Long>("bitpos", key, value, params) {

            @Override
            Long execute() {
                return getJedisCluster().bitpos(key, value, params);
            }

        });
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, int cursor) {
        return execute(new RedisCommand<ScanResult<Map.Entry<String, String>>>("hscan", key, cursor) {

            @Override
            ScanResult<Map.Entry<String, String>> execute() {
                return getJedisCluster().hscan(key, cursor);
            }

        });
    }

    @Override
    public ScanResult<String> sscan(String key, int cursor) {
        return execute(new RedisCommand<ScanResult<String>>("sscan", key, cursor) {

            @Override
            ScanResult<String> execute() {
                return getJedisCluster().sscan(key, cursor);
            }

        });
    }

    @Override
    public ScanResult<Tuple> zscan(String key, int cursor) {
        return execute(new RedisCommand<ScanResult<Tuple>>("zscan", key, cursor) {

            @Override
            ScanResult<Tuple> execute() {
                return getJedisCluster().zscan(key, cursor);
            }

        });
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor) {
        return execute(new RedisCommand<ScanResult<Map.Entry<String, String>>>("hscan", key, cursor) {

            @Override
            ScanResult<Map.Entry<String, String>> execute() {
                return getJedisCluster().hscan(key, cursor);
            }

        });
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
        return execute(new RedisCommand<ScanResult<Map.Entry<String, String>>>("hscan", key, cursor, params) {

            @Override
            ScanResult<Map.Entry<String, String>> execute() {
                return getJedisCluster().hscan(key, cursor, params);
            }

        });
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor) {
        return execute(new RedisCommand<ScanResult<String>>("sscan", key, cursor) {

            @Override
            ScanResult<String> execute() {
                return getJedisCluster().sscan(key, cursor);
            }

        });
    }

    @Override
    public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
        return execute(new RedisCommand<ScanResult<String>>("sscan", key, cursor, params) {

            @Override
            ScanResult<String> execute() {
                return getJedisCluster().sscan(key, cursor, params);
            }

        });
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor) {
        return execute(new RedisCommand<ScanResult<Tuple>>("zscan", key, cursor) {

            @Override
            ScanResult<Tuple> execute() {
                return getJedisCluster().zscan(key, cursor);
            }

        });
    }

    @Override
    public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
        return execute(new RedisCommand<ScanResult<Tuple>>("zscan", key, cursor, params) {

            @Override
            ScanResult<Tuple> execute() {
                return getJedisCluster().zscan(key, cursor, params);
            }

        });
    }

    @Override
    public Long pfadd(String key, String... elements) {
        return execute(new RedisCommand<Long>("pfadd", key, elements) {

            @Override
            Long execute() {
                return getJedisCluster().pfadd(key, elements);
            }

        });
    }

    @Override
    public long pfcount(String key) {
        return execute(new RedisCommand<Long>("pfcount", key) {

            @Override
            Long execute() {
                return getJedisCluster().pfcount(key);
            }

        });
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        return execute(new RedisCommand<Long>("geoadd", key, longitude, latitude, member) {

            @Override
            Long execute() {
                return getJedisCluster().geoadd(key, longitude, latitude, member);
            }

        });
    }

    @Override
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        return execute(new RedisCommand<Long>("geoadd", key, memberCoordinateMap) {

            @Override
            Long execute() {
                return getJedisCluster().geoadd(key, memberCoordinateMap);
            }

        });
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        return execute(new RedisCommand<Double>("geodist", key, member1, member2) {

            @Override
            Double execute() {
                return getJedisCluster().geodist(key, member1, member2);
            }

        });
    }

    @Override
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        return execute(new RedisCommand<Double>("geodist", key, member1, member2, unit) {

            @Override
            Double execute() {
                return getJedisCluster().geodist(key, member1, member2, unit);
            }

        });
    }

    @Override
    public List<String> geohash(String key, String... members) {
        return execute(new RedisCommand<List<String>>("geohash", key, members) {

            @Override
            List<String> execute() {
                return getJedisCluster().geohash(key, members);
            }

        });
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        return execute(new RedisCommand<List<GeoCoordinate>>("geopos", key, members) {

            @Override
            List<GeoCoordinate> execute() {
                return getJedisCluster().geopos(key, members);
            }

        });
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        return execute(new RedisCommand<List<GeoRadiusResponse>>("georadius", key, longitude, latitude, radius, unit) {

            @Override
            List<GeoRadiusResponse> execute() {
                return getJedisCluster().georadius(key, longitude, latitude, radius, unit);
            }

        });
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        return execute(new RedisCommand<List<GeoRadiusResponse>>("georadius", key, longitude, latitude, radius, unit, param) {

            @Override
            List<GeoRadiusResponse> execute() {
                return getJedisCluster().georadius(key, longitude, latitude, radius, unit, param);
            }

        });
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        return execute(new RedisCommand<List<GeoRadiusResponse>>("georadiusByMember", key, member, radius, unit) {

            @Override
            List<GeoRadiusResponse> execute() {
                return getJedisCluster().georadiusByMember(key, member, radius, unit);
            }

        });
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        return execute(new RedisCommand<List<GeoRadiusResponse>>("georadiusByMember", key, member, radius, unit, param) {

            @Override
            List<GeoRadiusResponse> execute() {
                return getJedisCluster().georadiusByMember(key, member, radius, unit, param);
            }

        });
    }

    @Override
    public List<Long> bitfield(String key, String... arguments) {
        return execute(new RedisCommand<List<Long>>("bitfield", key, arguments) {

            @Override
            List<Long> execute() {
                return getJedisCluster().bitfield(key, arguments);
            }

        });
    }

}
