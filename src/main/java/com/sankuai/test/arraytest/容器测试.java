package com.sankuai.test.arraytest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/4 14:24
 */
public class 容器测试 {

    /**
     * 内存分页，防止数组越界
     * @param offset
     * @param limit
     */
    public static void testSubList(int offset, int limit) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            list.add(i);
        }
        List<Integer> subList = list.subList(Math.min(offset, list.size()), Math.min(offset + limit, list.size()));
        System.out.println(subList.size());
        System.out.println(subList);
    }

    public static void main(String[] args) {
        容器测试.testSubList(00, 100);
    }
}
