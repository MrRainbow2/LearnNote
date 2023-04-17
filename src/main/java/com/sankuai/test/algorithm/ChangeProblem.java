package com.sankuai.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renxinlei
 * @version 1.0
 * description
 * 凑零钱问题：
 * 给你 k 种面值的硬币，每种硬币的数目是无限的，再给一个总金额amount,最少需要几枚硬币凑出该金额。
 * <p>
 * create date 2023/2/8 21:28
 */
public class ChangeProblem {

    public static void main(String[] args) {
        int[] coin = new int[]{1, 2, 5};
        int amount = 8;
        System.out.println(solutionOne(coin, amount));
    }

    /**
     * 暴力递归。
     * 每个硬币的解法都可以看作是当前 （amount - coin）的最少个数 + 1
     * 当 amount - coin < 0 时证明当前硬币无解。
     * <p>
     * 递归解法： 子问题重复计算，例如 amount = 10 ， coinArr = {1,2,5};
     * 10
     *                     1     2     5
     * 1层子问题          9       8       5
     *                1 2 5   1 2 5   1 2 5
     * 2层子问题       8 7 4   7 6 3   4 3 0
     * 可见第1层的 【8】与 第2层的【8】相同，计算逻辑重复，以此类推。
     * 故： 提效点为 如何解决子问题的重复结算，在这就需要我们把每次计算出的值记录下来。
     *
     * @param changeArr
     * @param amount
     * @return
     */
    public static int solutionOne(int[] changeArr, int amount) {
        int res = Integer.MAX_VALUE;
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        for (int coin : changeArr) {
            int childrenProblem = solutionOne(changeArr, amount - coin);
            if (childrenProblem == -1) {
                continue;
            }
            res = Math.min(res, childrenProblem + 1);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }

//    public static int solutionTwo(int[] changeArr, int amount) {
//        Map<Integer,Integer> amountMap = new HashMap<>();                                                                                                                                                                                                                                                 bgb                                                                                                                                                                                          g
//    }
}
