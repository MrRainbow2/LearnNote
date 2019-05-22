package com.sankuai.test.algorithm.sort;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/3/15 11:23
 * 冒泡排序
 * 时间复杂度：O(n^2)
 * 稳定性：稳定
 */
public class BubbleSort {
    static public void main(String[] args) {
        int[] array = {3, 2, 1, 5, 4, 6, 7, 8};
        BubbleSort.bubbleSort(array);
        for (int n : array) {
            System.out.println(n);
        }
    }

    static public int[] bubbleSort(int[] nums) {
        for (int index = 0; index < nums.length - 1; index++) {
            for (int index2 = 0; index2 < nums.length - 1 - index; index2++) {
                if (nums[index2] > nums[index2 + 1]) {
                    swap(nums, index2, index2 + 1);
                }
            }
        }
        return nums;
    }

    static public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
