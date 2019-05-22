package com.sankuai.test.algorithm.sort;

/**
 * 插入排序
 * <p>
 * 1. 从第一个元素开始，该元素可以认为已经被排序
 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 5. 将新元素插入到该位置后
 * 6. 重复步骤2~5
 */
public class InsertSort {
    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i; j >= 0; j--) {
                if (j > 0 && nums[j - 1] > temp) {
                    nums[j] = nums[j - 1];
                } else {
                    nums[j] = temp;
                    break;
                }
            }
        }
    }

    public static void insertSort2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j - 1] > nums[j]) {
                    swap(j - 1, j, nums);
                }
            }
        }
    }

    private static void swap(int index1, int index2, int[] nums) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    static public void main(String[] args) {
        int[] array = {3, 2, 1, 5, 4, 6, 7, 8};
        InsertSort.insertSort2(array);
        for (int n : array) {
            System.out.println(n);
        }
    }
}
