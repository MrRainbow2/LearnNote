package com.sankuai.test.algorithm.常见问题;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/25 17:18
 */
public class Problem {
    /**
     * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
     * <p>
     * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
     * <p>
     * 返回 承载所有人所需的最小船数。
     * <p>
     * LeetCode 881
     */
    static class Problem1 {
        public int numRescueBoats(int[] people, int limit) {
            if (people == null || people.length == 0) {
                return 0;
            }
            Arrays.sort(people);
            int leftIdx = 0;
            int rightIdx = people.length - 1;
            int result = 0;
            while (leftIdx < rightIdx) {
                if (people[leftIdx] + people[rightIdx] <= limit) {
                    leftIdx++;
                }
                rightIdx--;
                result++;
            }
            if (leftIdx == rightIdx) {
                result++;
            }
            return result;
        }
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     * <p>
     * LeetCode 42
     */
    static class Problem2 {
        /**
         * 柱子 X 能接多少水 = min(左边的最大值,右边的最大值) - X
         * 所以需要两个数组，分别记录每个index 其左边的最大值与右边的最大值。
         *
         * @param
         * @return
         */
        public int trap(int[] height) {
            if (height == null || height.length < 3) {
                return 0;
            }
            int n = height.length;
            int[] leftTopLimit = new int[n];
            leftTopLimit[0] = height[0];
            for (int idx = 1; idx < n; idx++) {
                leftTopLimit[idx] = Math.max(leftTopLimit[idx - 1], height[idx]);
            }
            int[] rightTopLimit = new int[n];
            rightTopLimit[n - 1] = height[n - 1];
            for (int idx = n - 2; idx >= 0; idx--) {
                rightTopLimit[idx] = Math.max(height[idx], rightTopLimit[idx + 1]);
            }
            int result = 0;
            for (int idx = 0; idx < n; idx++) {
                int leftLimit = leftTopLimit[idx];
                int rightLimit = rightTopLimit[idx];
                result += Math.max(Math.min(leftLimit, rightLimit) - height[idx], 0);
            }
            return result;
        }

        public int trap2(int[] height) {
            if (height == null || height.length < 3) {
                return 0;
            }
            int leftLimit = height[0];
            int rightLimit = height[height.length - 1];
            int leftPointer = 0;
            int rightPointer = height.length - 1;
            int result = 0;
            while (leftPointer <= rightPointer) {
                if (leftLimit <= rightLimit) {
                    result += Math.max(leftLimit - height[leftPointer], 0);
                    leftLimit = Math.max(leftLimit, height[leftPointer++]);
                } else {
                    result += Math.max(rightLimit - height[rightPointer], 0);
                    rightLimit = Math.max(rightLimit, height[rightPointer--]);
                }
            }
            return result;
        }

    }

    /**
     * leetCode 325
     * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。
     */
    static class Problem3 {
        public int maxSubArrayLen(int[] nums, int k) {
            if (nums == null || nums.length < 1) {
                return 0;
            }
            HashMap<Integer, Integer> preSumIndexMap = new HashMap<>();
            preSumIndexMap.put(0, -1);
            int sum = 0;
            int result = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                sum += nums[idx];
                int preSum = sum - k;
                if (preSumIndexMap.containsKey(preSum)) {
                    result = Math.max(result, idx - preSumIndexMap.get(preSum));
                }
                preSumIndexMap.putIfAbsent(sum, idx);
            }
            return result;
        }
    }

    /**
     * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
     * <p>
     * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
     * <p>
     * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
     * <p>
     * 请你返回「表现良好时间段」的最大长度。
     * <p>
     * 输入：hours = [9,9,6,0,6,6,9]
     * 输出：3
     * 解释：最长的表现良好时间段是 [9,9,6]。
     * <p>
     * 输入：hours = [6,6,6]
     * 输出：0
     * <p>
     * leetCode 1124
     * <p>
     * 题解： 当前问题可以转化为：工作时间大于 8 小时的记为 1 不大于8小时的记为 -1
     * 可以求做和大于等于1的最大序列；
     */
    static class Problem4 {
        public static int longestWPI1(int[] hours) {
            // key : 某个前缀和
            // value : 这个前缀和最早出现的位置
            HashMap<Integer, Integer> map = new HashMap<>();
            // 0这个前缀和，最早出现在哪？一个数也没有的时候
            map.put(0, -1);
            int ans = 0;
            int sum = 0;
            for (int i = 0; i < hours.length; i++) {
                sum += hours[i] > 8 ? 1 : -1;
                if (sum > 0) {
                    // 0...i i+1
                    ans = i + 1;
                } else {
                    // sum = -4
                    // -5最早出现在哪 j  j+1...i
                    if (map.containsKey(sum - 1)) {
                        ans = Math.max(ans, i - map.get(sum - 1));
                    }
                }
                if (!map.containsKey(sum)) {
                    map.put(sum, i);
                }
            }
            return ans;
        }

        public int longestWPI(int[] hours) {
            if (hours == null || hours.length < 1) {
                return 0;
            }
            HashMap<Integer, Integer> preSumIndexMap = new HashMap<>();
            preSumIndexMap.put(0, -1);
            int sum = 0;
            int result = 0;
            for (int idx = 0; idx < hours.length; idx++) {
                sum += hours[idx] > 8 ? 1 : -1;
                if (sum > 0) {
                    result = idx + 1;
                } else {
                    if (preSumIndexMap.containsKey(sum - 1)) {
                        result = Math.max(idx - preSumIndexMap.get(sum - 1), result);
                    }
                }
                preSumIndexMap.putIfAbsent(sum, idx);
            }
            return result;
        }
    }

    /**
     * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
     * 如果不存在，则向缓存中插入该组 key-value 。
     * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     * <p>
     * leetCode 146
     */
    static class Problem5 {
        class LRUCache {
            private int capacity;
            private final HashMap<Integer, DoubleLinkedNode> table;
            private final DoubleLinkedList list;

            public LRUCache(int capacity) {
                this.capacity = capacity;
                this.table = new HashMap<>();
                this.list = new DoubleLinkedList();
            }

            public int get(int key) {
                DoubleLinkedNode node = table.get(key);
                if (node != null) {
                    list.moveNodeToTail(node);
                    return node.value;
                }
                return -1;
            }

            public void put(int key, int value) {
                DoubleLinkedNode node = table.get(key);
                if (node != null) {
                    node.value = value;
                    list.moveNodeToTail(node);
                } else {
                    if (list.size == capacity) {
                        DoubleLinkedNode head = list.head;
                        list.removeHead();
                        table.remove(head.key);
                    }
                    DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
                    list.addNode(newNode);
                    table.put(key, newNode);
                }
            }

            class DoubleLinkedNode {
                int value;
                int key;
                DoubleLinkedNode pre;
                DoubleLinkedNode next;

                DoubleLinkedNode(int key, int val) {
                    this.value = val;
                    this.key = key;
                }
            }

            class DoubleLinkedList {
                int size;
                DoubleLinkedNode head;
                DoubleLinkedNode tail;

                DoubleLinkedList() {
                    head = null;
                    tail = null;
                    this.size = 0;
                }

                DoubleLinkedList(DoubleLinkedNode h, DoubleLinkedNode t) {
                    this.head = h;
                    this.tail = t;
                }

                public void addNode(DoubleLinkedNode node) {
                    if (head == null) {
                        head = node;
                        tail = node;
                    } else {
                        node.pre = tail;
                        tail = node;
                        node.pre.next = node;
                    }
                    size++;
                }

                public int removeHead() {
                    if (head == null) {
                        return -1;
                    } else if (head == tail) {
                        DoubleLinkedNode result = head;
                        head = null;
                        tail = null;
                        size--;
                        return result.value;
                    } else {
                        DoubleLinkedNode result = head;
                        head = head.next;
                        result.next = null;
                        head.pre = null;
                        size--;
                        return result.value;
                    }
                }

                public void moveNodeToTail(DoubleLinkedNode doubleLinkedNode) {
                    if (tail == doubleLinkedNode) {
                        return;
                    }
                    if (doubleLinkedNode == head) {
                        head = head.next;
                        head.pre = null;
                    } else {
                        doubleLinkedNode.pre.next = doubleLinkedNode.next;
                        doubleLinkedNode.next.pre = doubleLinkedNode.pre;
                    }
                    doubleLinkedNode.next = null;
                    doubleLinkedNode.pre = tail;
                    tail = doubleLinkedNode;
                    doubleLinkedNode.pre.next = doubleLinkedNode;
                }
            }
        }

        /**
         * https://leetcode.cn/problems/subarray-sum-equals-k/
         * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
         */
        static class Problem6 {
            public int subarraySum(int[] nums, int k) {
                if (nums == null || nums.length < 1) {
                    return 0;
                }
                HashMap<Integer, Integer> preSumCountMap = new HashMap<>();
                preSumCountMap.put(0, 1);
                int sum = 0;
                int result = 0;
                for (int idx = 0; idx < nums.length; idx++) {
                    sum += nums[idx];
                    if (preSumCountMap.containsKey(sum - k)) {
                        result += preSumCountMap.get(sum - k);
                    }
                    int count = preSumCountMap.getOrDefault(sum, 0);
                    preSumCountMap.put(sum, count + 1);
                }
                return result;
            }
        }
    }


    public static void main(String[] args) {
        Problem5.LRUCache cache = new Problem5().new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);// 2->3->4
        cache.get(4);
        cache.get(3);
        cache.get(2);//4->3->2
        cache.get(1);//null
        cache.put(5, 5);//5->3->2
        cache.get(1);//null
        cache.get(2);//2
        cache.get(3);//3
        cache.get(4);//null
        cache.get(5);//5
    }
}
