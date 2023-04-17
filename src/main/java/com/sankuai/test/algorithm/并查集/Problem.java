package com.sankuai.test.algorithm.并查集;

import java.io.*;
import java.util.HashMap;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/26 17:22
 */
public class Problem {


    /**
     * n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。
     * <p>
     * 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。
     * 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。
     * <p>
     * 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。 每次交换可选择任意两人，让他们站起来交换座位。
     * <p>
     * 示例1：
     * row = [0,2,1,3]
     * 输出：1
     * 情侣分组： 0:[0,1],  1: [2,3];
     * 由于 0组和1组情侣混合，导致需要交换一次座位  2与1交换，生成[0,1,2,3]。
     * <p>
     * 示例2:
     * row = [0,5,1,3,4,2]
     * 情侣分组：0:[0,1],  1:[2,3],  2:[4,5] 三组
     * 由于0，1，2三组情侣互相混合，  导致需要交换两次座位   5 与 2 交换 生成 [0,2,1,3,4,5], 然后 2与1交换 生成[0，1，2，3，4，5]
     * <p>
     * 由上诉可知，每个混合组的最少交换次数与每个混合组的元素有关，交换次数为混合组元素（n - 1）
     * <p>
     * 如果 n = 9;
     * 1,2,3,4,5,6,7,8,9
     * 总共分为以下 k组：k = 6
     * [1,2,3] [4,5]  [6]  [7]  [8]  [9]
     * 3 - 1   2 -1  1-1  1-1  1-1  1-1
     * <p>
     * 最少交换次数 = (3+2+1+1+1+1) - (1+1+1+1+1+1)
     * (3+2+1+1+1+1) => n
     * (1+1+1+1+1+1) => 集合个数
     * 所以最少交换次数为：  n（分组数）- k（集合个数）
     * leetCode 765
     */
    static class Problem1 {
        class UnionFind {
            int[] father;
            int[] num;
            int[] help;
            int setNum;

            public UnionFind(int size) {
                this.num = new int[size];
                this.father = new int[size];
                this.help = new int[size];
                for (int idx = 0; idx <= size; idx++) {
                    father[idx] = idx;
                    num[idx] = 1;
                }
                setNum = size;
            }

            public boolean isSameSet(int a, int b) {
                return findFather(a) == findFather(b);
            }

            private int findFather(int param) {
                int size = 0;
                while (father[param] != param) {
                    help[size++] = param;
                    param = father[param];
                }
                while (size > 0) {
                    father[help[--size]] = param;
                }
                return param;
            }

            public void union(int a, int b) {
                int fatherA = findFather(a);
                int fatherB = findFather(b);
                if (fatherA != fatherB) {
                    if (num[fatherA] >= num[fatherB]) {
                        father[fatherB] = fatherA;
                        num[fatherA] += num[fatherB];
                    } else {
                        father[fatherA] = fatherB;
                        num[fatherB] += num[fatherA];
                    }
                    setNum--;
                }

            }
        }

        public int minSwapsCouples(int[] row) {
            int n = row.length;
            if (n <= 2) {
                return 0;
            }
            UnionFind unionFind = new UnionFind(n / 2);
            for (int idx = 0; idx < row.length; idx += 2) {
                int groupA = row[idx] / 2;
                int groupB = row[idx + 1] / 2;
                unionFind.union(groupA, groupB);
            }
            return n / 2 - unionFind.setNum;
        }
    }


    /**
     * leetCode 947
     */
    static class Problem2 {
        class UFS {
            int[] father;
            int[] nums;
            int[] help;
            int setNums;

            UFS(int num) {
                father = new int[num];
                nums = new int[num];
                help = new int[num];
                setNums = num;
                for (int idx = 0; idx < num; idx++) {
                    father[idx] = idx;
                    nums[idx] = 1;
                }
            }

            public boolean isSameSet(int x, int b) {
                return findFather(x) == findFather(b);
            }

            private int findFather(int x) {
                int size = 0;
                while (father[x] != x) {
                    help[size++] = x;
                    x = father[x];
                }
                while (size > 0) {
                    father[help[--size]] = x;
                }
                return x;
            }

            public void union(int a, int b) {
                int fx = findFather(a);
                int fy = findFather(b);
                if (fx == fy) {
                    return;
                }
                if (nums[fx] >= nums[fy]) {
                    father[fy] = fx;
                    nums[fx] += nums[fy];
                } else {
                    father[fx] = fy;
                    nums[fy] += nums[fx];
                }
                setNums--;
            }
        }

        public int removeStones(int[][] stones) {
            if (stones == null || stones.length < 2) {
                return 0;
            }
            HashMap<Integer, Integer> xFirstIdxMap = new HashMap<>();
            HashMap<Integer, Integer> yFirstIdxMap = new HashMap<>();
            UFS unionFind = new UFS(stones.length);
            for (int idx = 0; idx < stones.length; idx++) {
                if (xFirstIdxMap.containsKey(stones[idx][0])) {
                    unionFind.union(xFirstIdxMap.get(stones[idx][0]), idx);
                }
                if (yFirstIdxMap.containsKey(stones[idx][1])) {
                    unionFind.union(yFirstIdxMap.get(stones[idx][1]), idx);
                }
                xFirstIdxMap.putIfAbsent(stones[idx][0], idx);
                yFirstIdxMap.putIfAbsent(stones[idx][1], idx);
            }
            return stones.length - unionFind.setNums;
        }

    }

    /**
     * 4 5
     * 1 1 2
     * 2 2 3
     * 2 1 3
     * 1 1 1
     * 1 2 3
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
       System.out.println(new Problem2().removeStones(new int[][]{{0,1},{1,1},{1,0}})); ;
    }
}
