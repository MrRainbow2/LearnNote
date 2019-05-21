package com.sankuai.test.reflect;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/12/27 14:46
 */
public interface ICalculator {
    String name = "default_calculator";

    default String getName() {
        return name;
    }

    int cal(int var1,int var2);
}
