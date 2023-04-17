package com.sankuai.test.algorithm.查找;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/21 20:57
 */
public class 二分查找 {

    /**
     * 步骤和问题：
     * 一个数字如
     * 658 --> 650 + 65 + 8 = 721
     * 那么 721 就是 658的步骤和
     */
    class Problem1 {
        public boolean solution2(int param){
            if (param == 0){
                return true;
            }
            return false;
        }
        public boolean solution(int param) {
            if (param == 0) {
                return false;
            }
            int L = 0;
            int R = param;
            while (L <= R) {
                int mid = L + (R - L) / 2;
                int sum = calSum(mid);
                if (sum == param) {
                    return true;
                } else if (sum < param) {
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return false;
        }

        public int calSum(int n) {
            int sum = 0;
            while (n != 0) {
                sum += n;
                n /= 10;
            }
            return sum;
        }
    }

    /**
     * LeetCode 875
     * 吃香蕉问题
     */
    class Problem2 {
        /**
         * 给定一个速度，返回吃完香蕉的时间
         * 速度的范围为：0 到 max(piles)-> 每一堆香蕉都能在一小时内吃完的速度。
         *
         * @param piles
         * @param h
         * @return
         */
        public int minEatingSpeed(int[] piles, int h) {
            int maxSpeed = 1;
            for (int num : piles) {
                maxSpeed = Math.max(num, maxSpeed);
            }
            int left = 1;
            int right = maxSpeed;
            int result = maxSpeed;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int time = calTimeWithSpeed(piles, mid);
                if (time <= h) {
                    result = Math.min(result, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }

        private int calTimeWithSpeed(int[] piles, int speed) {
            int offset = speed - 1;
            int sumTime = 0;
            for (int pile : piles) {
                sumTime += (pile + offset) / speed;
            }
            return sumTime;
        }
    }


    /**
     * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
     * <p>
     * 设计一个算法使得这 m 个子数组各自和的最大值最小。
     * <p>
     * eg.
     * 输入：nums = [7,2,5,10,8], m = 2
     * 输出：18
     * 解释：
     * 一共有四种方法将 nums 分割为 2 个子数组。
     * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/split-array-largest-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * <p>
     * 解题思路：
     * 将其转化为：
     * 给定一个值，告诉我可以分成几 N个 子序列来完成。
     * 这个值的范围为 0 ～ sum(nums)
     * 如果 N 大于 m 则这个值给定的有点小。
     * 如果 N 小于 m 这这个值给定的有点大。
     * 通过二分查找出合适的值。
     */
    class Problem3 {


        public int splitArray(int[] nums, int k) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            int left = 0;
            int right = sum;
            int result = Integer.MAX_VALUE;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int count = calCountBySum(nums, mid);
                if (count > k) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                    result = mid;
                }
            }
            return result;
        }

        /**
         * 根据给定一个总和，计算需要几个子序列才能实现。
         *
         * @param nums
         * @param sum
         * @return
         */
        private int calCountBySum(int[] nums, int sum) {
            for (int num : nums) {
                if (sum < num) {
                    return Integer.MAX_VALUE;
                }
            }
            int number = nums[0];
            int result = 1;
            for (int idx = 1; idx < nums.length; idx++) {
                if (number + nums[idx] <= sum) {
                    number += nums[idx];
                } else {
                    result++;
                    number = nums[idx];
                }
            }
            return result;
        }
    }

    /**
     * 查找一个有序数列中，大于一个数字的最左下标是多少
     */
    class Problem4 {
        public int solution(int[] arr, int param) {
            if (arr == null || arr.length == 0) {
                return -1;
            }
            int left = 0;
            int right = arr.length;
            int result = Integer.MIN_VALUE;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] <= param) {
                    result = Math.max(result, mid);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            System.out.println(left);
            return result == Integer.MIN_VALUE ? -1 : result;
        }
    }

    public static void main(String[] args) {
        二分查找 bSearch = new 二分查找();
//        Problem1 problem1 = bSearch.new Problem1();
//        System.out.println(problem1.solution(5342));

//        Problem2 problem2 = bSearch.new Problem2();
//        System.out.println(problem2.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));

//        Problem3 problem3 = bSearch.new Problem3();
//        System.out.println(problem3.splitArray(new int[]{7, 2, 5, 10, 8}, 2));


        Problem4 problem4 = bSearch.new Problem4();
        System.out.println(problem4.solution(new int[]{1, 2, 2, 2, 7, 8, 10, 11}, 2));
    }
}






















