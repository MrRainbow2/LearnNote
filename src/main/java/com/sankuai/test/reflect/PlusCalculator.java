package com.sankuai.test.reflect;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2018/12/27 14:50
 */
public class PlusCalculator implements ICalculator {

    @Override
    public String getName() {
        return "plus_calculator";
    }

    @Override
    public int cal(int var1, int var2) {
        return var1 + var2;
    }
}
