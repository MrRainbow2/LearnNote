package com.sankuai.test.redis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/5/8 16:38
 */
public class RedisClient {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 10, 3L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(3));
        for (int i = 0; i < 10; i++) {
            threadClient threadClient = new threadClient();
            threadClient.setThreadName("线程" + i);
            threadPool.execute(threadClient);
        }
    }
}
