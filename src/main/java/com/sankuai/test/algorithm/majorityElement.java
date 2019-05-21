package com.sankuai.test.algorithm;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class majorityElement {
    static public void main(String[] args) {
        System.out.println(new majorityElement().majorityElementAnswer1(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(new majorityElement().majorityElementAnswer2(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    /**
     * 将数组排序后进行比对，当有任意数字出现次数大于 n/2将其返回
     *
     * @param nums
     * @return
     */
    public int majorityElementAnswer1(int[] nums) {
        Arrays.sort(nums);
        int limit = nums.length / 2;
        if (limit == 0)
            return nums[0];
        int count = 1;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == nums[index + 1]) {
                count++;
            }
            if (count > limit) {
                return nums[index];
            }
        }
        return 0;
    }

    /**
     * 由于众数定义是出现次数大于n/2,故众数出现次数-其他数字出现次数肯定大于0;
     *
     * @param nums
     * @return
     */
    public int majorityElementAnswer2(int[] nums) {
        int s = 1;
        int num = nums[0];
        for (int index = 1; index < nums.length; index++) {
            if (num == nums[index]) {
                s++;
            } else {
                s--;
                if (s == 0) {
                    num = nums[index];
                    s = 1;
                }
            }
        }
        return num;
    }

}
