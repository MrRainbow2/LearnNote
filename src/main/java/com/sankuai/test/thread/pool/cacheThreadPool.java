package com.sankuai.test.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/12/28 10:36
 */
public class cacheThreadPool {

    /**
     * 线程1~3会先进入线程池，线程4~6进入阻塞队列。之后的线程会再次进入线程池，<br/>
     * 由于线程池最大容量为 corePoolSize + maximumPoolSize + 阻塞队列size，<br/>
     * 所以第17个线程execute时会触发拒绝机制。
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 10, 3L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(3));
        for (int i = 1; i <= 17; i++) {
            threadDemo thread = new threadDemo("thread@Num" + i);
            threadPool.execute(thread);
        }
    }
}
