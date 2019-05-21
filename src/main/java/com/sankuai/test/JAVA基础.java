package com.sankuai.test;

import java.util.HashTable;
import java.util.Map;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/4/13 11:45
 */
public class JAVA基础 {
    public static void main(String[] args) {
        JAVA基础 test = new JAVA基础();
//        test.Type();
//        test.cal();
        test.calRightMove();
    }

    public void calRightMove() {
        Map map = new HashTable();
        map.get("22");
    }

    public void cal() {
        int n = -2;
        System.out.print("数字-2右移1位后结果为：");
        System.out.println(n >> 1);

        System.out.print("数字-2无符号右移1位后结果为：");
        System.out.println(n >>> 1);

        System.out.print("整数最大为：");
        System.out.println(Integer.MAX_VALUE << 1);
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).toCharArray().length);
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE).toCharArray().length);
    }

    public void Type() {
        long DAY = 24 * 60 * 60 * 100000L;
        System.out.println(DAY);
    }
}
