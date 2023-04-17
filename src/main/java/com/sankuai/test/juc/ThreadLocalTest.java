package com.sankuai.test.juc;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/11 17:22
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        threadLocal.set(2);
    }
}
