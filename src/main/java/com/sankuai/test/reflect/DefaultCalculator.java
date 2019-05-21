package com.sankuai.test.reflect;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/12/27 15:24
 */
public class DefaultCalculator implements ICalculator {
    @Override
    public int cal(int var1, int var2) {
        return var1 * var2;
    }
}
