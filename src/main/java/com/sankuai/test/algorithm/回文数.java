package com.sankuai.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/4/8 20:12
 */
public class 回文数 {
    static class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            } else if (x < 10) {
                return true;
            } else {
                Stack<Integer> stack = new Stack<>();
                List<Integer> list = new ArrayList<>();
                while (x > 0) {
                    int num = x % 10;
                    list.add(num);
                    stack.push(num);
                    x /= 10;
                }
                for (Integer num : list) {
                    if (!num.equals(stack.pop())) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
    public static void main(String[] args){
        回文数.Solution solution = new Solution();
        System.out.println(solution.isPalindrome(110011));
    }
}
