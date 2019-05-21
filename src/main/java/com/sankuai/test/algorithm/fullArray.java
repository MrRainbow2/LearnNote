package com.sankuai.test.algorithm;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/1/10 16:08
 * <p>
 * 输入一个数组，输出其元素的全排列
 * eg. 输入 [1,2,3]
 * 输出： 123 132 213 231 312 321
 */
public class fullArray {

    public void swap(int index1, int index2, int[] array) {
        int swapParam = array[index1];
        array[index1] = array[index2];
        array[index2] = swapParam;
    }

    public void operate(int current, int end, int[] array) {

    }

}
