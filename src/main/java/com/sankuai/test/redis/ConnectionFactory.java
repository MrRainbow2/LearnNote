package com.sankuai.test.redis;

import redis.clients.jedis.Jedis;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/5/8 16:33
 */
public class ConnectionFactory {
    private volatile static Jedis jedis;
    private static Object initLock = new Object();
    private static final String redisUrl = "localhost";

    public static Jedis getJedis() {
        return new Jedis(redisUrl);
    }
}
