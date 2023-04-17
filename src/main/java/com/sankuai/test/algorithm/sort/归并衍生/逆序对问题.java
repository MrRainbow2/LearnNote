package com.sankuai.test.algorithm.sort.归并衍生;

/**
 * 在一个数组中，
 * 任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的，就称为逆序对
 * 返回数组中所有的逆序对
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,5,6,4]
 * 输出: 5
 * [7,5] ,[7,6] [7,4]
 * [5,6]
 * [6,4]
 * 共5对
 * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class 逆序对问题 {
    public static void main(String[] args) {
        System.out.println(new 逆序对问题().reversePairs(new int[]{7,5,6,4}));
    }

    public int reversePairs(int[] nums) {
        int L = 0;
        int R = nums.length - 1;
        return process(nums, L, R);
    }

    private int process(int[] arr, int L, int R) {
        if (L >= R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);

        return process(arr, L, mid)
                +
                process(arr, mid + 1, R)
                +
                merge(arr, L, mid, R);
    }

    private int merge(int[] arr, int L, int mid, int R) {
        if (L >= R) {
            return 0;
        }
        int leftTemp = mid;
        int rightTemp = R;
        int result = 0;
        int[] help = new int[R - L + 1];
        int i = help.length - 1;
        while (leftTemp >= L && rightTemp >= (mid + 1)) {
            if (arr[leftTemp] > arr[rightTemp]) {
                result += (rightTemp - mid);
                help[i--] = arr[leftTemp--];
            } else {
                help[i--] = arr[rightTemp--];
            }
        }
        while (leftTemp >= L) {
            help[i--] = arr[leftTemp--];
        }
        while (rightTemp >= (mid + 1)) {
            help[i--] = arr[rightTemp--];
        }
        for (int idx = 0; idx < help.length; idx++) {
            arr[L + idx] = help[idx];
        }
        return result;
    }
}
