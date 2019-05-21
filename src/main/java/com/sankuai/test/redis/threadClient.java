package com.sankuai.test.redis;

import redis.clients.jedis.Jedis;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/5/8 16:56
 */
public class threadClient implements Runnable {
    private String threadName;

    @Override
    public void run() {
        System.out.println(this.getThreadName() + "Start....");
        Jedis jedis = ConnectionFactory.getJedis();
        while (true) {
            if (DistributeLock.getLock(jedis, "lock5", "lock5")) {
                System.out.println(this.getThreadName() + "获取到锁");
                System.out.println(this.getThreadName() + "链接结果" + jedis.ping());
                System.out.println(this.getThreadName() + jedis.hgetAll("myhash"));
                DistributeLock.releaseDistributedLock(jedis, "lock5", "lock5");
                break;
            }
        }

    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
