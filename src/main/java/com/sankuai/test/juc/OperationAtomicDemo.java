package com.sankuai.test.juc;

/**
 * 多线程操作原子性：i++是否是原子操作。 为什么不是原子操作
 */
public class OperationAtomicDemo {
    private int i = 0;

    public void incrementDemo() {
       synchronized (this){
           i++;
       }
    }
}
