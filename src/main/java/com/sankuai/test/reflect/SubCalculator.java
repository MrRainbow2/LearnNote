package com.sankuai.test.reflect;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/12/27 14:51
 */
public class SubCalculator implements ICalculator {
    @Override
    public String getName() {
        return "subscribe_calculator";
    }

    @Override
    public int cal(int var1, int var2) {
        return var1 - var2;
    }
}
