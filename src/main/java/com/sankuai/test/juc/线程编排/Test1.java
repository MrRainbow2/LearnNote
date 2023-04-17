package com.sankuai.test.juc.线程编排;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/30 16:58
 */
public class Test1 {
    public static ReentrantLock lockA = new ReentrantLock();
    public static Condition con = lockA.newCondition();
    public static ReentrantLock lockB = new ReentrantLock();
    public static ReentrantLock lockC = new ReentrantLock();
    public static AtomicInteger condition = new AtomicInteger(1);

    public static void main(String[] args) {

        Thread a = new Thread(() -> {
            do {
                if (condition.get() % 3 == 1) {
                    System.out.println("A");
                    condition.getAndIncrement();
                } else {
                    Thread.yield();
                }
            } while (condition.get() <= 30);
        });

        Thread b = new Thread(() -> {
            do {
                if (condition.get() % 3 == 2) {
                    System.out.println("B");
                    condition.getAndIncrement();
                } else {
                    Thread.yield();
                }
            } while (condition.get() <= 30);
        });

        Thread c = new Thread(() -> {
            do {
                if (condition.get() % 3 == 0) {
                    System.out.println("C");
                    condition.getAndIncrement();
                } else {
                    Thread.yield();
                }
            } while (condition.get() <= 30);
        });

        a.start();
        b.start();
        c.start();
    }
}
