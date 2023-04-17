package com.sankuai.test.algorithm;

public class 众数 {
    /**
     * 众数定义，一个数组，长度为length，出现次数 n > length/2 的数字即为众数。
     */

    public Integer solution(int[] inputArr) {
        int length = inputArr.length;
        Integer result = null;
        int count = 0;
        for (int target : inputArr) {
            if (result == null || count == 0) {
                result = target;
                count++;
                continue;
            }
            if (result == target) {
                count++;
            } else {
                count--;
            }
        }
        int sum = 0;
        for (int cur : inputArr) {
            if (cur == result) {
                sum++;
            }
        }
        return sum > length / 2 ? result : null;
    }


}
