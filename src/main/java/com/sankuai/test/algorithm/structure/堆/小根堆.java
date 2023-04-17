package com.sankuai.test.algorithm.structure.堆;

import javax.swing.text.StyleContext;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author renxinlei
 * @version 1.0
 * description TODO
 * create date 2023/3/21 23:45
 */
public class 小根堆 {

    /**
     * 最大重合线段问题
     */
    class Problem1 {


    }

    /**
     * 分金条问题
     */
    class Problem2 {

    }


    /**
     * 有 n名工人。给定两个数组quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。
     * <p>
     * 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
     * <p>
     * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
     * 工资组中的每名工人至少应当得到他们的最低期望工资。
     * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。在实际答案的 10-5 以内的答案将被接受。。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-cost-to-hire-k-workers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * quality = [10,20,5], wage = [70,50,30], k = 2
     * 输出： 105.00000
     */
    class Problem3 {

        class Employee {
            int quality;
            int wage;
            double factor;

            Employee(int quality, int wage) {
                this.factor = (double) wage / (double) quality;
                this.quality = quality;
                this.wage = wage;
            }
        }

        public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
            Employee[] employeeArr = new Employee[quality.length];
            for (int idx = 0; idx < quality.length; idx++) {
                employeeArr[idx] = new Employee(quality[idx], wage[idx]);
            }
            Arrays.sort(employeeArr, (a, b) -> a.factor <= b.factor ? -1 : 1);
            Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            double ans = Double.MAX_VALUE;
            for (int idx = 0, qualitySum = 0; idx < quality.length; idx++) {
                if (maxHeap.size() < k) {
                    maxHeap.add(employeeArr[idx].quality);
                    qualitySum += employeeArr[idx].quality;
                    if (maxHeap.size() == k) {
                        ans = Math.min(qualitySum * employeeArr[idx].factor, ans);
                    }
                } else {
                    if (employeeArr[idx].quality < maxHeap.peek()) {
                        qualitySum += employeeArr[idx].quality - maxHeap.poll();
                        maxHeap.add(employeeArr[idx].quality);
                        ans = Math.min(qualitySum * employeeArr[idx].factor, ans);
                    }

                }
            }
            return ans;
        }
    }

    /**
     * top k
     * leetCode: 215
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * <p>
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * <p>
     * 输入: [3,2,1,5,6,4], k = 2
     * 输出: 5
     * <p>
     * 输入: [3,2,3,1,2,4,5,5,6], k = 4
     * 输出: 4
     */
    static class Problem5 {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minTopHeap = new PriorityQueue<>();
            int size = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                if (size < k) {
                    minTopHeap.add(nums[idx]);
                    size++;
                } else {
                    if (nums[idx] > minTopHeap.peek()) {
                        minTopHeap.poll();
                        minTopHeap.add(nums[idx]);
                    }
                }
            }
            return minTopHeap.poll();
        }
    }

    public static void main(String[] args) {
//        System.out.println(new 小根堆().new Problem3().mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
        System.out.println(new Problem5().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }


}
