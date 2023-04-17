package com.sankuai.test.algorithm;

/**
 * https://leetcode.cn/problems/coin-change/
 * 硬币问题：
 * <p>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 */

public class 硬币问题 {


    /**
     * 暴力递归方式
     * 超时
     */
    class Solution1 {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            if (amount < 0) {
                return -1;
            }
            int result = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subResult = coinChange(coins, amount - coin);
                if (subResult == -1) {
                    continue;
                }
                result = Math.min(result, subResult + 1);
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }

    /**
     * 使用数组记录计算过的分支，进行剪支。
     */
    class Solution2 {
        public int coinChange(int[] coins, int amount) {
            int[] recordList = new int[amount + 1];
            for (int idx = 0; idx <= amount; idx++) {
                recordList[idx] = Integer.MAX_VALUE;
            }
            return solution(coins, amount, recordList);
        }

        public int solution(int[] coins, int amount, int[] record) {
            if (amount == 0) {
                return 0;
            }
            if (amount < 0) {
                return -1;
            }
            if (record[amount] != Integer.MAX_VALUE) {
                return record[amount];
            }
            int result = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subResult = solution(coins, amount - coin, record);
                if (subResult == -1) {
                    continue;
                }
                result = Math.min(result, subResult + 1);
            }
            if (result != Integer.MAX_VALUE) {
                record[amount] = result;
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }


    class Solution3 {
        public int coinChange(int[] coins, int amount) {
            int[] recordList = new int[amount + 1];
            for (int idx = 0; idx <= amount; idx++) {
                recordList[idx] = amount + 1;
            }
            recordList[0] = 0;
            for (int idx = 1; idx <= amount; idx++) {//从1到amount 分别算出其需要多少个硬币能够构成
                for (int coin : coins) {
                    if (idx - coin < 0) continue;
                    recordList[idx] = Math.min(recordList[idx - coin] + 1, recordList[idx]);
                }
            }
            return recordList[amount] == amount + 1 ? -1 : recordList[amount];
        }
    }

    class Solution4 {
        public int coinChange(int[] coins, int amount) {
            int[] recordList = new int[amount + 1];
            for (int idx = 0; idx <= amount; idx++) {
                recordList[idx] = amount + 1;
            }
            recordList[0] = 0;
            for (int idx = 1; idx <= amount; idx++) {//从1到amount 分别算出其需要多少个硬币能够构成
                for (int coin : coins) {
                    if (idx - coin < 0) continue;
                    recordList[idx] = Math.min(recordList[idx - coin] + 1, recordList[idx]);
                }
            }
            return recordList[amount] == amount + 1 ? -1 : recordList[amount];
        }
    }

    public static void main(String[] args) {
        硬币问题.Solution3 solution = new 硬币问题().new Solution3();
        System.out.println(solution.coinChange(new int[]{186, 419, 83, 408}, 6249));
    }
}

