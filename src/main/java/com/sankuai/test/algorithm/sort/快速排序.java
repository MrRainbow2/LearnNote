package com.sankuai.test.algorithm.sort;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/4/20 12:41
 */
public class 快速排序 {

    public static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(arr, left, right);
        sort(arr, left, mid - 1);
        sort(arr, mid + 1, right);
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int leftIdx = left;
        int rightIdx = right - 1;
        while (leftIdx <= rightIdx) {
            while (leftIdx <= rightIdx && arr[leftIdx] <= pivot) {
                leftIdx++;
            }
            while (leftIdx <= rightIdx && arr[rightIdx] > pivot) {
                rightIdx--;
            }
            if (leftIdx < rightIdx) {
                swap(arr, leftIdx, rightIdx);
            }
            swap(arr, leftIdx, right);
        }
        return leftIdx;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] param = new int[]{2, 1, 4, 6, 7, 22, 8, 3};
        快速排序.sort(param, 0, param.length - 1);
        for (int i: param){
            System.out.println(i);
        }
    }
}
