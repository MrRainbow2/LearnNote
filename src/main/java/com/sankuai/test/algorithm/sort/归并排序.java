package com.sankuai.test.algorithm.sort;

import java.util.Arrays;

/**
 * @author renxinlei
 * @version 1.0
 * description 归并排序递归实现
 */
public class 归并排序 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 5, 4, 2, 1, 0};
        new 归并排序().sort(arr);

        Arrays.stream(arr).forEach(System.out::println);
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, right, mid);
    }

    public void process2(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        if (arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int leftStart = 0;
            while (leftStart < N) {
                int mid = leftStart + mergeSize - 1;
                if (mid >= N) {
                    break;
                }
                int rightEnd = Math.min(mid + mergeSize, N - 1);
                merge(arr, leftStart, rightEnd, mid);
                leftStart = rightEnd + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }

    }

    public void merge(int[] arr, int left, int right, int mid) {
        if (left == right) {
            return;
        }
        int leftTemp = left;
        int rightTemp = mid + 1;
        int i = 0;
        int[] help = new int[right - left + 1];
        while (leftTemp <= mid && rightTemp <= right) {
            help[i++] = arr[leftTemp] <= arr[rightTemp] ? arr[leftTemp++] : arr[rightTemp++];
        }
        while (leftTemp <= mid) {
            help[i++] = arr[leftTemp++];
        }
        while (rightTemp <= right) {
            help[i++] = arr[rightTemp++];
        }
        for (int idx = 0; idx < i; idx++) {
            arr[left + idx] = help[idx];
        }
    }
}
