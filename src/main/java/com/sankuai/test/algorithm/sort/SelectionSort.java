package com.sankuai.test.algorithm.sort;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/3/15 11:41
 * 选择排序 ：在未排序序列中每次找到最大值(最小值)查到已排序序列的尾端。
 * 时间复杂度 ： O（n^2）
 * 稳定性：不稳定
 */
public class SelectionSort {
    static public void main(String[] args) {
        int[] array = {3, 2, 1, 5, 4, 8, 8, 7, 6, 2, 33, 4};
        new SelectionSort().selectionSort(array);
        for (int n : array) {
            System.out.println(n);
        }
    }

    public int[] selectionSort(int[] nums) {
        int index = 0;
        int location = 0;
        while (index < nums.length - 1) {
            int minValue = Integer.MAX_VALUE;
            for (int i = index; i < nums.length; i++) {
                if (nums[i] < minValue) {
                    minValue = nums[i];
                    location = i;
                }
            }
            swap(nums, index, location);
            index++;
        }
        return nums;
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
