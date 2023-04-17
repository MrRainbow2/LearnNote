package com.sankuai.test.algorithm.动态规划;

import java.util.Arrays;

/**
 * LeetCode : 300
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 */
public class 最长递增子序列 {

    /**
     * dp动态规划，按照以每一个数字结尾计算其最长子序列的长度。
     * dp数组记录每一个元素作为子序列结尾的长度
     */
    static class solution1 {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int result = 1;
            for (int idx = 1; idx < nums.length; idx++) {
                int max = 1;
                for (int idx2 = idx - 1; idx2 >= 0; idx2--) {
                    if (nums[idx] > nums[idx2]) {
                        max = Math.max(max, dp[idx2] + 1);
                    }
                }
                dp[idx] = max;
                result = Math.max(max, result);
            }
            return result;
        }
    }


    static class solutionStudy {
        /**
         * 使用dp数组记录下来，以每个数字结尾时，最大的长度
         *
         * @param nums
         * @return
         */
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }
            int[] dp = new int[nums.length];
            int result = 1;
            dp[0] = 1;
            for (int idx = 1; idx < nums.length; idx++) {
                int subMaxLength = 1;
                for (int j = idx - 1; j >= 0; j--) {
                    if (nums[idx] > nums[j]) {
                        subMaxLength = Math.max(subMaxLength, dp[j] + 1);
                    }
                }
                result = Math.max(subMaxLength, result);
                dp[idx] = subMaxLength;
            }
            return result;
        }
    }

    /**
     * ends数组记录每个长度对应的最小的值
     */
    static class solution2 {
        public int lengthOfLIS(int[] nums) {
            int[] ends = new int[nums.length];
            ends[0] = nums[0];
            int left = 0;
            int right = 0;
            int max = 1;
            for (int idx = 1; idx < nums.length; idx++) {
                int l = left;
                int r = right;
                int leftIndex = Integer.MAX_VALUE;
                while (l <= r) { // 寻找ends数组中，大于等于 num[idx] 的最左边下标。
                    int mid = l + (r - l) / 2;
                    if (ends[mid] >= nums[idx]) {
                        leftIndex = Math.min(leftIndex, mid);
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
                // ends数组这种没有大于等于 num[idx]的数字，则证明 num[idx]为当前最大的数字，可以构成当前right+1 长度的序列；
                if (leftIndex == Integer.MAX_VALUE) {
                    leftIndex = right + 1;
                }
                ends[leftIndex] = nums[idx];
                max = Math.max(max, leftIndex + 1);
                right = Math.max(right, leftIndex);
            }
            return max;
        }
    }

    /**
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * <p>
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * <p>
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * <p>
     * 注意：不允许旋转信封。
     * <p>
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     * <p>
     * 输入：envelopes = [[1,1],[1,1],[1,1]]
     * 输出：1
     * <p>
     * https://leetcode.cn/problems/russian-doll-envelopes/
     * 最长子序列变种，信封问题
     */
    static class solution3 {
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes == null) {
                return 0;
            }
            //将信封的宽按照从小到大排序，如果信封宽相同，则按照高的从大到小排序
            //输入示例： [[5,4],[6,4],[6,7],[2,3]]
            //排序之后： [[2,3],[5,4],[6,7],[6,4]]
            //排完序之后，查找高的最长递增子序列，即可获取到最大信封重叠。
            Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
            int[] ends = new int[envelopes.length];
            ends[0] = envelopes[0][1];
            int result = 1;
            int rightPointer = 0;
            for (int idx = 1; idx < envelopes.length; idx++) {
                int l = 0;
                int r = rightPointer;
                int leftIndex = Integer.MAX_VALUE;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (ends[mid] >= envelopes[idx][1]) {
                        r = mid - 1;
                        leftIndex = Math.min(leftIndex, mid);
                    } else {
                        l = mid + 1;
                    }
                }
                if (leftIndex == Integer.MAX_VALUE) {
                    leftIndex = rightPointer + 1;
                }
                ends[leftIndex] = envelopes[idx][1];
                rightPointer = Math.max(rightPointer, leftIndex);
                result = Math.max(result, leftIndex + 1);
            }
            return result;
        }

    }


    public static void main(String[] args) {
        solution3 solution2 = new solution3();
//        System.out.println(solution2.maxEnvelopes(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
