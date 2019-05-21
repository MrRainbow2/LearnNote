package com.sankuai.test.thread.pool;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/12/29 15:14
 */
public class threadDemo implements Runnable {

    String threadName;

    public threadDemo() {
    }

    public threadDemo(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(this.getThreadName() + "Start....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getThreadName() + "Finish....");

    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
