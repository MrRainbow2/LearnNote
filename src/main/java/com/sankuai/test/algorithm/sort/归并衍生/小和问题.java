package com.sankuai.test.algorithm.sort.归并衍生;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 */
public class 小和问题 {

    public int solution(int[] arr) {
        int L = 0;
        int R = arr.length - 1;
        return process(arr, L, R);
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
        int[] help = new int[R - L + 1];
        int leftTemp = L;
        int rightTemp = mid + 1;
        int result = 0;
        int i = 0;
        while (leftTemp <= mid && rightTemp <= R) {
            if (arr[leftTemp] < arr[rightTemp]) {
                result += arr[leftTemp] * (R - rightTemp + 1);
                help[i++] = arr[leftTemp++];
            } else {
                help[i++] = arr[rightTemp++];
            }
        }
        while (leftTemp <= mid) {
            help[i++] = arr[leftTemp++];
        }
        while (rightTemp <= R) {
            help[i++] = arr[rightTemp++];
        }
        for (int idx = 0; idx < help.length; idx++) {
            arr[L + idx] = help[idx];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new 小和问题().solution(new int[]{2, 1, 4, 6, 9, 8, 10}));
    }
}
