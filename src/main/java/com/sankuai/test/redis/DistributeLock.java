package com.sankuai.test.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/5/9 10:34
 */
public class DistributeLock {

    private static final Long RELEASE_SUCCESS = 1L;

    public static boolean getLock(Jedis jedis,String key, String value) {
        if (jedis.setnx(key, value) == 1L) {
            return true;
        }
        return false;
    }

    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}
