package org.txazo.wyot.jedis;

public abstract class RedisScripts {

    public static final String SCRIPT_HINCR;
    public static final String SCRIPT_HINCR2;
    public static final String SCRIPT_LIKE_UNLIKE_COUNT_INCR;
    public static final String SCRIPT_LIKE_UNLIKE_COUNT_INCR2;

    static {
        SCRIPT_HINCR = getHincrScript();
        SCRIPT_HINCR2 = getHincr2Script();
        SCRIPT_LIKE_UNLIKE_COUNT_INCR = getLikeUnlikeCountIncrScript();
        SCRIPT_LIKE_UNLIKE_COUNT_INCR2 = getLikeUnlikeCountIncr2Script();
    }

    private static String getHincrScript() {
        String script = "if redis.call(\"hexists\", KEYS[1], ARGV[1]) == 1";
        script += " then";
        script += " return redis.call(\"hincrby\", KEYS[1], ARGV[1], 1)";
        script += " else";
        script += " return 0";
        script += " end";
        return script;
    }

    private static String getHincr2Script() {
        String script = "if redis.call(\"hexists\", KEYS[1], ARGV[1]) == 1";
        script += " then";
        script += " return redis.call(\"hincrby\", KEYS[1], ARGV[1], 1)";
        script += " else";
        script += " return redis.call(\"hset\", KEYS[1], ARGV[1], ARGV[2])";
        script += " end";
        return script;
    }

    private static String getLikeUnlikeCountIncrScript() {
        String script = "if redis.call(\"hexists\", KEYS[1], ARGV[1]) == 1";
        script += " then";
        script += " local value = redis.call(\"hget\", KEYS[1], ARGV[1])";
        script += " local index = tonumber(ARGV[2])";
        script += " local splits = {}";
        script += " for str in string.gmatch(value, \"(%d+)-*\") do";
        script += "     table.insert(splits, str)";
        script += " end";
        script += " local count = tonumber(splits[index])";
        script += " splits[index] = tostring(count + 1)";
        script += " value = splits[1]..\"-\"..splits[2]";
        script += " return redis.call(\"hset\", KEYS[1], ARGV[1], value)";
        script += " else";
        script += " return -1";
        script += " end";
        return script;
    }

    private static String getLikeUnlikeCountIncr2Script() {
        String script = "if redis.call(\"hexists\", KEYS[1], ARGV[1]) == 1";
        script += " then";
        script += " local value = redis.call(\"hget\", KEYS[1], ARGV[1])";
        script += " local index = tonumber(ARGV[2])";
        script += " local splits = {}";
        script += " for str in string.gmatch(value, \"(%d+)-*\") do";
        script += "     table.insert(splits, str)";
        script += " end";
        script += " local count = tonumber(splits[index])";
        script += " splits[index] = tostring(count + 1)";
        script += " value = splits[1]..\"-\"..splits[2]";
        script += " return redis.call(\"hset\", KEYS[1], ARGV[1], value)";
        script += " else";
        script += " return redis.call(\"hset\", KEYS[1], ARGV[1], ARGV[3])";
        script += " end";
        return script;
    }

}
