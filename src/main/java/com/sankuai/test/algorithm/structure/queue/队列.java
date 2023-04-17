package com.sankuai.test.algorithm.structure.queue;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2022/10/12 22:28
 */
public class 队列 {
    static class ArrQueue {
        int[] queue;
        private int size;
        private int head;
        private int tail;
        private final int limit;


        public ArrQueue(int limit) {
            head = tail = 0;
            queue = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int num) {
            if (isFull()) {
                throw new UnsupportedOperationException("队列已满");
            }
            size++;
            queue[tail] = num;
            tail = calIdx(++tail);
        }

        public int pop() {
            if (isEmpty()) {
                throw new UnsupportedOperationException("队列已空");
            }
            size--;
            int result = queue[head];
            head = calIdx(++head);
            return result;
        }

        private int calIdx(int idx) {
            return idx % limit;
        }

    }

    static class LinkedQueue {
        static class LinkNode {
            int value;
            LinkNode pre;
            LinkNode next;

            public LinkNode(int value) {
                this.value = value;
            }
        }

        private int size;
        private LinkNode head;
        private LinkNode tail;
        private final int limit;

        public LinkedQueue(int limit) {
            head = tail = null;
            this.limit = limit;
            size = 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int num) {
            if (isFull()) {
                throw new UnsupportedOperationException("队列已满");
            }
            LinkNode cur = new LinkNode(num);
            if (isEmpty()) {
                head = cur;
                tail = cur;
                cur.pre = null;
                cur.next = null;
            } else {
                cur.next = tail.next;
                tail.next = cur;
                cur.pre = tail;
                tail = cur;
            }
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new UnsupportedOperationException("队列已空");
            }
            size--;
            int result = head.value;
            head = head.next;
            return result;
        }
    }

    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println(queue.isFull());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.add(11);
        queue.add(22);
        queue.add(33);
        queue.add(44);
        queue.add(55);
        System.out.println(queue.pop());

    }
}
