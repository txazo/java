package org.txazo.wyot.jedis;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import redis.clients.jedis.JedisClusterScriptingCommands;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.MultiKeyJedisClusterCommands;

abstract class AbstractRedisClient extends RedisJsonSerializer
        implements JedisClusterHolder, JedisCommands, MultiKeyJedisClusterCommands, JedisClusterScriptingCommands {

    protected <T> T execute(RedisCommand<T> command) {
        Transaction t = Cat.newTransaction("Redis", command.getCommand());
        try {
            Cat.logTrace("Redis.Command", buildCommand(command.getCommand(), command.getArgs()));
            T ret = command.execute();
            t.setStatus(Transaction.SUCCESS);
            return ret;
        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
            throw e;
        } finally {
            t.complete();
        }
    }

    private static String buildCommand(String command, Object[] args) {
        StringBuilder sb = new StringBuilder(command);
        if (args != null && args.length > 0) {
            for (Object o : args) {
                if (o.getClass().isArray()) {
                    Object[] arr = (Object[]) o;
                    if (arr != null && arr.length > 0) {
                        for (Object a : arr) {
                            sb.append(" ").append(a);
                        }
                    }
                } else {
                    sb.append(" ").append(o);
                }
            }
        }
        return sb.toString();
    }

}
