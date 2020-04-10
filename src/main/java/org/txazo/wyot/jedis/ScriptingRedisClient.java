package org.txazo.wyot.jedis;

import java.util.List;

abstract class ScriptingRedisClient extends AbstractRedisClient {

    @Override
    public Object eval(String script, int keyCount, String... params) {
        return execute(new RedisCommand<Object>("eval", script, keyCount, params) {

            @Override
            Object execute() {
                return getJedisCluster().eval(script, keyCount, params);
            }

        });
    }

    @Override
    public Object eval(String script, List<String> keys, List<String> args) {
        return execute(new RedisCommand<Object>("eval", script, keys, args) {

            @Override
            Object execute() {
                return getJedisCluster().eval(script, keys, args);
            }

        });
    }

    @Override
    public Object eval(String script, String key) {
        return execute(new RedisCommand<Object>("eval", script, key) {

            @Override
            Object execute() {
                return getJedisCluster().eval(script, key);
            }

        });
    }

    @Override
    public Object evalsha(String script, String key) {
        return execute(new RedisCommand<Object>("evalsha", script, key) {

            @Override
            Object execute() {
                return getJedisCluster().evalsha(script, key);
            }

        });
    }

    @Override
    public Object evalsha(String sha1, List<String> keys, List<String> args) {
        return execute(new RedisCommand<Object>("evalsha", sha1, keys, args) {

            @Override
            Object execute() {
                return getJedisCluster().evalsha(sha1, keys, args);
            }

        });
    }

    @Override
    public Object evalsha(String sha1, int keyCount, String... params) {
        return execute(new RedisCommand<Object>("evalsha", sha1, keyCount, params) {

            @Override
            Object execute() {
                return getJedisCluster().evalsha(sha1, keyCount, params);
            }

        });
    }

    @Override
    public Boolean scriptExists(String sha1, String key) {
        return execute(new RedisCommand<Boolean>("scriptExists", sha1, key) {

            @Override
            Boolean execute() {
                return getJedisCluster().scriptExists(sha1, key);
            }

        });
    }

    @Override
    public List<Boolean> scriptExists(String key, String... sha1) {
        return execute(new RedisCommand<List<Boolean>>("scriptExists", key, sha1) {

            @Override
            List<Boolean> execute() {
                return getJedisCluster().scriptExists(key, sha1);
            }

        });
    }

    @Override
    public String scriptLoad(String script, String key) {
        return execute(new RedisCommand<String>("scriptLoad", script, key) {

            @Override
            String execute() {
                return getJedisCluster().scriptLoad(script, key);
            }

        });
    }

}
