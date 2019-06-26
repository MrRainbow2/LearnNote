package com.sankuai.test.algorithm;

import java.util.Arrays;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/3/18 14:13
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3], m = 3
 * nums2 = [2,5,6,0,0,0],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class mergeSortList {
    static public void main(String[] args) {
        int[] array2 = {1, 2, 3, 5, 6};
        int[] array1 = {4, 0, 0, 0, 0, 0};
        new mergeSortList().merge(array1, 1, array2, 5);
        for (int num : array1) {
            System.out.println(num);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0 || nums2.length == 0) {
            return;
        }
        int index = m + n - 1;

    }

    private void swap(int[] array, int[] array2, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array2[index2];
        array2[index2] = temp;
    }
}
