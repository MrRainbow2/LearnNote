package com.sankuai.test.algorithm;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/19 14:31
 */
public class 只出现一次的数 {

    public int singleNumber(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) {
            arr[i] = 0;
        }
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & 1 << i) != 0) {
                    arr[i] += 1;
                }
            }
        }
        int result = 0;
        for (int idx = 0; idx < 32; idx++) {
            if (arr[idx] % 3 != 0) {
                result = result | (1 << idx);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        只出现一次的数 solution = new 只出现一次的数();
        int result = solution.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 100});
        System.out.println(result);
    }
}
