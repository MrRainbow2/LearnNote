package com.sankuai.test.algorithm;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/7 17:54
 */
public class _01寻找出现奇数次的数字 {

    /**
     * problem 1
     * 一个数组中，有一种数字出现了奇数次，其他数字都出现了偶数次，试着找出这个数字。
     * eg [101,1,1,2,2,3,3,34,34,5555,5555,5555,5555];
     * 输出：101
     */
    public static int solution(int[] args) {
        if (args == null || args.length == 0) {
            return 0;
        }
        int result = 0;
        for (int param : args) {
            result ^= param;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{101, 1, 1, 2, 2, 3, 3, 34, 34, 5555, 5555, 5555, 5555}));

        System.out.println(solution2(1));
    }

    /**
     * problem 2 有一个数字，找其二进制数字最右边的 1，并将其输出。
     * eg . input : 1  output : 1 => 因为1的二进制为  000000001
     * input: 6  output : 2;  6的二进制为 00000000110
     */
    public static int solution2(int arg) {
        if (arg == 0) {
            return 0;
        }
        return (arg & -arg);
    }

}
