package com.sankuai.test.algorithm.structure;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/12 11:26
 */
public class 位运算 {
    public static void main(String[] args) {
        int n = Double.valueOf(Math.pow(2, 15)).intValue();
        System.out.println((n << 16) + 2);
        for (int i = 31; i >= 0; i--) {
            if (((n << 16) + 2 & 1 << i) == 0) {
                System.out.print("0");
            } else {
                System.out.print("1");
            }
        }
    }
}
